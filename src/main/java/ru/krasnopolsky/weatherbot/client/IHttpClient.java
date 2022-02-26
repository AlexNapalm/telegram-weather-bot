package ru.krasnopolsky.weatherbot.client;

import java.net.http.HttpResponse;

public interface IHttpClient {

    HttpResponse<String> doRequest(String parameter);
}
