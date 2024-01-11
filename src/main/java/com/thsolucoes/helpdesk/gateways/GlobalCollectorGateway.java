/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.gateways;

import com.thsolucoes.helpdesk.services.ClientSocketService;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author NTH05
 */
public class GlobalCollectorGateway {

    private final static int DELIMITER = 10;
    private final static JSONObject output = new JSONObject();

    public static void main(String[] args) throws URISyntaxException {
        ClientSocketService client = new ClientSocketService();

        ScheduledExecutorService globalScheduler = Executors.newScheduledThreadPool(1);
        Runnable globalCommand = () -> {
            try {
                JSONObject software = SoftwareCollectorGateway.collect();
                JSONObject hardware = HardwareCollectorGateway.collect();
                Thread.sleep(10000);

                String cpuOverloadStatus = hardware.getJSONObject("cpu").get("overload").toString();
                String ramOverloadStatus = hardware.getJSONObject("ram").get("overload").toString();
                
                if ("true".equals(cpuOverloadStatus)) {
                
                }
                
                if ("true".equals(ramOverloadStatus)) {
                
                }
                
                output.put("hardware", hardware);
                output.put("software", software);
                output.put("agent_uuid", "190847389235");

                client.transmit(output.toString());
                System.out.println(output);
            } catch (InterruptedException ex) {
                Logger.getLogger(GlobalCollectorGateway.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        globalScheduler.scheduleAtFixedRate(globalCommand, 0, DELIMITER, TimeUnit.SECONDS);
    }
}
