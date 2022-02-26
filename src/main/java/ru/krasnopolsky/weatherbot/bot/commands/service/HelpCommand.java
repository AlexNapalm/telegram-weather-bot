package ru.krasnopolsky.weatherbot.bot.commands.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.krasnopolsky.weatherbot.Utils;

/**
 * "/help" command
 */
public class HelpCommand extends ServiceCommand {

    private final Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        logger.debug(String.format("User %s. Started execution of command %s", userName,
                this.getCommandIdentifier()));
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Simple weather bot, that gets current weather for entered location\n\n" +
                        "❗*Commands list*\n" +
                        "/start - start\n" +
                        "/help - help\n\n" +
                "Your ads could be here");
        logger.debug(String.format("User %s. Finished execution of command %s", userName,
                this.getCommandIdentifier()));
    }
}
