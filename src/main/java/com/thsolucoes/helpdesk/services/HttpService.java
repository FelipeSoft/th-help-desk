/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author NTH05
 */
public class HttpService {

    private final String BASE_URL;

    public HttpService(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public HttpResponse get(String route) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(this.BASE_URL + route))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return null;
        }
    }

    public HttpResponse post(String route, String body) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(this.BASE_URL + route))
                    .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                    .header("Content-Type", "application/json")
                    .build();
            
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return null;
        }
    }
}
