/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.gateways;

import com.thsolucoes.helpdesk.domain.Output;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.thsolucoes.helpdesk.hardware.CentralProcessingUnit;
import com.thsolucoes.helpdesk.hardware.RandomMemory;
import com.thsolucoes.helpdesk.services.ClientSocketService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author NTH05
 */
public class HardwareCollectorGateway {

    private static int delimiter = 10;
    private static List<Double> logs = new ArrayList<>();
    private static JSONObject cpuJSON = new JSONObject();
    private static JSONObject ramJSON = new JSONObject();
    private static JSONObject storageJSON = new JSONObject();
    private static JSONObject output = new JSONObject();

    public static void main(String[] args) {
        ClientSocketService client = new ClientSocketService();

        ScheduledExecutorService outputScheduler = Executors.newScheduledThreadPool(1);

        // OUTPUT
        Runnable out = () -> {
            try {
                client.send(output);
            } catch (IOException e) {}
        };
        
        outputScheduler.scheduleAtFixedRate(out, 0, delimiter, TimeUnit.SECONDS);

        // CPU        
        ScheduledExecutorService centralProcessingUnitScheduler = Executors.newScheduledThreadPool(1);
        Runnable cpu = () -> {
            Output track = CentralProcessingUnit.track();
            if (track.logs.size() == delimiter) {
                double acc = 0;
                double max = max(track.logs);
                double average = average(acc, track.logs);

                if (average >= 60) {
                    cpuJSON.put("overload", true);
                } else {
                    cpuJSON.put("overload", false);
                }

                cpuJSON.put("peak", max);
                cpuJSON.put("logs", track.logs);
                cpuJSON.put("lastUsage", track.usage);
                cpuJSON.put("average", average);

                track.logs = new ArrayList<>();
                output.put("cpu", cpuJSON);
            }
        };
        centralProcessingUnitScheduler.scheduleAtFixedRate(cpu, 0, 1, TimeUnit.SECONDS);

        // RAM
        ScheduledExecutorService randomMemoryScheduler = Executors.newScheduledThreadPool(1);
        Runnable ram = () -> {
            Output track = RandomMemory.track();
            if (track.logs.size() == delimiter) {
                double acc = 0;
                double max = max(track.logs);
                double average = average(acc, track.logs);

                ramJSON.put("peak", max);
                ramJSON.put("logs", track.logs);
                ramJSON.put("lastUsage", track.usage);
                ramJSON.put("average", average);
                output.put("ram", ramJSON);

            }
        };
        randomMemoryScheduler.scheduleAtFixedRate(ram, 0, 1, TimeUnit.SECONDS);
        // STORAGE
        //ScheduledExecutorService storageUnitScheduler = Executors.newScheduledThreadPool(1);
        //Runnable storage = () -> {
        //};
        //storageUnitScheduler.scheduleAtFixedRate(storage, 0, 1, TimeUnit.SECONDS);
    }

    private static double max(List<Double> list) {
        double max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            double current = list.get(i);
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

    private static double average(double accumulator, List<Double> list) {
        for (double log : list) {
            accumulator += log;
        }
        return accumulator / delimiter;
    }
}
