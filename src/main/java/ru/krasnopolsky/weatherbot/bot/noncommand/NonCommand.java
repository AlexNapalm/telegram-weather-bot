package ru.krasnopolsky.weatherbot.bot.noncommand;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.krasnopolsky.weatherbot.client.IHttpClient;
import ru.krasnopolsky.weatherbot.client.WeatherHttpClient;
import ru.krasnopolsky.weatherbot.mapper.ResponseMapper;

import java.net.http.HttpResponse;

/**
 * Processing non-command message (plain text, starting not with "/")
 */
public class NonCommand {

    private final Logger logger = LoggerFactory.getLogger(NonCommand.class);
    private final IHttpClient client;
    private final ResponseMapper mapper;

    public NonCommand() {
        this.client = new WeatherHttpClient();
        this.mapper = new ResponseMapper();
    }

    @SneakyThrows
    public String nonCommandExecute(String userName, String text) {
        logger.debug(String.format("User %s. Start processing message \"%s\", that is not command",
                userName, text));
        if (text.startsWith("/")) {
            return String.format("Unrecognized command \"%s\", press /help to see commands list", text);
        }

        HttpResponse<String> response = client.doRequest(text);
        if (response.statusCode() != 200) {
            return mapper.toError(response.body());
        }
        return mapper.toStringRepresentation(response.body());
    }
}
