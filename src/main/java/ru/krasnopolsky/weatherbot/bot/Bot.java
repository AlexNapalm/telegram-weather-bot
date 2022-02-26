package ru.krasnopolsky.weatherbot.bot;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.krasnopolsky.weatherbot.Utils;
import ru.krasnopolsky.weatherbot.bot.commands.service.HelpCommand;
import ru.krasnopolsky.weatherbot.bot.commands.service.StartCommand;
import ru.krasnopolsky.weatherbot.bot.noncommand.NonCommand;

import java.io.FileInputStream;
import java.util.Properties;

public class Bot extends TelegramLongPollingCommandBot {

    private static final String PROPERTIES_FILE_NAME = "app.properties";
    private static final String BOT_NAME_KEY = "bot.name";
    private static final String BOT_TOKEN_KEY = "bot.token";

    private final Logger logger = LoggerFactory.getLogger(Bot.class);
    private Properties properties;
    private final NonCommand nonCommand;

    public Bot() {
        super();
        this.nonCommand = new NonCommand();
        loadProperties();

        register(new StartCommand("start", "Start"));
        register(new HelpCommand("help","Help"));

        logger.info("Bot is created");
    }

    @SneakyThrows
    private void loadProperties() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + PROPERTIES_FILE_NAME;

        this.properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));
    }

    @Override
    public String getBotToken() {
        return properties.getProperty(BOT_TOKEN_KEY);
    }

    @Override
    public String getBotUsername() {
        return properties.getProperty(BOT_NAME_KEY);
    }

    /**
     * Answer to a non-command request
     */
    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        String userName = Utils.getUserName(msg);

        String answer = nonCommand.nonCommandExecute(userName, msg.getText());
        setAnswer(chatId, userName, answer);
    }

    /**
     * Send answer
     * @param chatId chat id
     * @param userName user name
     * @param text answer text
     */
    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            logger.error(String.format("Error %s. Message, that is not command. User: %s", e.getMessage(),
                    userName));
            e.printStackTrace();
        }
    }
}
