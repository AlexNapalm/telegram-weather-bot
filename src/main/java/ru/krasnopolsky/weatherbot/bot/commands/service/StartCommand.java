package ru.krasnopolsky.weatherbot.bot.commands.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.krasnopolsky.weatherbot.Utils;

/**
 * /start command
 */
public class StartCommand extends ServiceCommand {

    private final Logger logger = LoggerFactory.getLogger(StartCommand.class);

    public StartCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        logger.debug(String.format("User %s. Started command %s", userName,
                this.getCommandIdentifier()));
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Let's start! If you need help, click /help");
        logger.debug(String.format("User %s. Finished command %s", userName,
                this.getCommandIdentifier()));
    }
}
