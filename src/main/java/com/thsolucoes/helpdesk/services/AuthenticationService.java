/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.services;

import java.net.http.HttpResponse;

/**
 *
 * @author NTH05
 */
public class AuthenticationService {
    private String token;
    private final HttpService HttpServiceHandler;

    public AuthenticationService(HttpService HttpServiceHandler) {
        this.HttpServiceHandler = HttpServiceHandler;
    }

    public void invokeToken(String token) {
        this.token = token;
    }

    public boolean attempt() {
        String body = "";
        HttpResponse response = this.HttpServiceHandler.post(this.token, body);
        return response.statusCode() == 200;
    }
}
