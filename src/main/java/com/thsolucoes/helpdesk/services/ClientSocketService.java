/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.json.JSONObject;

/**
 *
 * @author NTH05
 */
public class ClientSocketService {

    private final String host = "127.0.0.1";
    private final int port = 8345;
    private final Socket socket;

    public ClientSocketService() {
        try {
            this.socket = new Socket(this.host, this.port);
        } catch (IOException e) {
            System.out.println("Não foi possível se conectar ao servidor socket.");
            throw new RuntimeException("Falha ao criar a conexão com o servidor socket.");
        }
    }

    public void send(JSONObject json) throws IOException {
        OutputStream output = this.socket.getOutputStream();
        String stringified = json.toString();
        output.write(stringified.getBytes());
    }

    public String receive() throws IOException {
        InputStream input = this.socket.getInputStream();
        byte[] buffer = new byte[1024];
        int bytes = input.read(buffer);
        String data = new String(buffer, 0, bytes);
        return data;
    }
}
