package ru.krasnopolsky.weatherbot;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public class Utils {

    /**
     * Build user name.
     * @param msg message
     */
    public static String getUserName(Message msg) {
        return getUserName(msg.getFrom());
    }

    /**
     * Build user name. If nickname is present, then it will be used. Otherwise - use last name and first name
     * @param user user
     */
    public static String getUserName(User user) {
        return (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
    }
}
