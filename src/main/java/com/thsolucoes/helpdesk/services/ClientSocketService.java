package com.thsolucoes.helpdesk.services;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class ClientSocketService extends WebSocketClient {

    private static final String SERVER_URI = "ws://localhost:8345"; 
    private static final long RECONNECT_INTERVAL = 5000;

    private Timer reconnectTimer;

    public ClientSocketService() throws URISyntaxException {
        super(new URI(SERVER_URI));
        connect(); 
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Conexão aberta");
        cancelReconnect(); 
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Conexão fechada. Tentando reconectar...");
        scheduleReconnect();
    }

    @Override
    public void onError(Exception ex) {
    }

    private void scheduleReconnect() {
        if (reconnectTimer == null) {
            reconnectTimer = new Timer();
            reconnectTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!isOpen()) {
                        System.out.println("Tentando reconectar...");
                        try {
                            reconnectBlocking();
                        } catch (InterruptedException e) {
                        }
                    } else {
                        System.out.println("Conexão estabelecida com sucesso.");
                        cancelReconnect();
                    }
                }
            }, 0, RECONNECT_INTERVAL);
        }
    }

    private void cancelReconnect() {
        if (reconnectTimer != null) {
            reconnectTimer.cancel();
            reconnectTimer.purge();
            reconnectTimer = null;
        }
    }

    public void transmit(String json) {
        if (isOpen()) {
            send(json);
        } else {
            System.out.println("Conexão não está aberta.");
        }
    }
}
