package ru.krasnopolsky.weatherbot.bot.noncommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Processing non-command message (plain text, starting not with "/")
 */
public class NonCommand {

    private final Logger logger = LoggerFactory.getLogger(NonCommand.class);

    public String nonCommandExecute(Long chatId, String userName, String text) {
        logger.debug(String.format("User %s. Start processing message \"%s\", that is not command",
                userName, text));

        return "Text that you typed: *" + text + "*";
    }
}
