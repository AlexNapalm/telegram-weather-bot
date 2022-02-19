package ru.krasnopolsky.weatherbot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.krasnopolsky.weatherbot.bot.Bot;

import java.util.Map;

public class Main {

    private static final Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot("NapalmBurnBot", "1947025002:AAFwpVQMYAdX8010_nAfRmLMbWcCuUDjbS4"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
