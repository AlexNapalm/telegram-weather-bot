package ru.krasnopolsky.weatherbot.client;

import com.google.common.base.Strings;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class WeatherHttpClient implements IHttpClient {

    private static final String PROPERTIES_FILE_NAME = "app.properties";
    private static final String API_ENDPOINT_KEY = "api.endpoint";
    private static final String API_TOKEN_KEY = "api.token";

    private String apiEndpoint;
    private String apiToken;

    public WeatherHttpClient() {
        loadProperties();
    }

    @SneakyThrows
    private void loadProperties() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + PROPERTIES_FILE_NAME;

        Properties properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));
        this.apiEndpoint = properties.getProperty(API_ENDPOINT_KEY);
        this.apiToken = properties.getProperty(API_TOKEN_KEY);
        validateApiAndToken();
    }

    private void validateApiAndToken() {
        if (Strings.isNullOrEmpty(apiEndpoint) || Strings.isNullOrEmpty(apiToken)) {
            throw new IllegalArgumentException("API endpoint or token can't be empty");
        }
    }

    @Override
    @SneakyThrows
    public HttpResponse<String> doRequest(String cityName) {
        HttpClient client = HttpClient.newHttpClient();

        URI uri = new URIBuilder(apiEndpoint)
                .addParameter("key", apiToken)
                .addParameter("q", cityName)
                .addParameter("aqi", "no")
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
