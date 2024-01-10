/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.gateways;

import com.thsolucoes.helpdesk.services.ClientSocketService;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/**
 *
 * @author NTH05
 */
public class GlobalCollectorGateway {

    private final static int DELIMITER = 10;
    private final static JSONObject output = new JSONObject();

    public static void main(String[] args) {
        ClientSocketService client = new ClientSocketService();

        ScheduledExecutorService globalScheduler = Executors.newScheduledThreadPool(1);
        Runnable globalCommand = () -> {
            JSONObject software = SoftwareCollectorGateway.collect();
            JSONObject hardware = HardwareCollectorGateway.collect();
            
            output.put("hardware", hardware);
            output.put("software", software);
            output.put("agent_uuid", "190847389235");
            
            try {
                client.send(output);
            } catch (IOException e) {
            }
        };

        globalScheduler.scheduleAtFixedRate(globalCommand, 0, DELIMITER, TimeUnit.SECONDS);
    }
}
