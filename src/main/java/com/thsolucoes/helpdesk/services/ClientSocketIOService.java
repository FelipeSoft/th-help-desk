package com.thsolucoes.helpdesk.services;

import io.socket.client.IO;
import io.socket.client.Socket;
import java.net.URISyntaxException;
import org.json.JSONObject;

public class ClientSocketIOService {

    private Socket socket;

    public ClientSocketIOService(String serverUrl) throws URISyntaxException {
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        this.socket = IO.socket(serverUrl, opts);

        socket.on(Socket.EVENT_CONNECT, (Object... args) -> {
            System.out.println("Conectado ao servidor Socket.IO");
        }).on("message", (Object... args) -> {
            System.out.println("Recebeu um evento personalizado: " + args[0]);
        });
    }

    public void connect() {
        System.out.println("Conexão estabelecida");
        socket.connect();
    }

    public void disconnect() {
        System.out.println("Conexão encerrada");
        socket.disconnect();
    }

    public Socket getSocket() {
        return socket;
    }

    public void send(JSONObject json) {
        System.out.println("Sending message: " + json);
        socket.emit("message", json);
    }
}
