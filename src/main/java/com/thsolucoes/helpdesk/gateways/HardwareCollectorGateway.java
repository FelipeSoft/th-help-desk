/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.gateways;

import com.thsolucoes.helpdesk.domain.StorageUnit.OutputSU;
import com.thsolucoes.helpdesk.domain.Output;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.thsolucoes.helpdesk.hardware.CentralProcessingUnit;
import com.thsolucoes.helpdesk.hardware.RandomMemory;
import com.thsolucoes.helpdesk.hardware.StorageUnit;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author NTH05
 */
public class HardwareCollectorGateway {

    private final static int DELIMITER = 10;
    private final static JSONObject cpuJSON = new JSONObject();
    private final static JSONObject ramJSON = new JSONObject();
    private final static JSONObject storageJSON = new JSONObject();
    private final static JSONObject output = new JSONObject();

    public static JSONObject collect() {
        // CPU        
        ScheduledExecutorService centralProcessingUnitScheduler = Executors.newScheduledThreadPool(1);
        Runnable cpuCommand = () -> {
            Output track = CentralProcessingUnit.track();
            if (track.logs.size() == DELIMITER) {
                double acc = 0;
                double max = max(track.logs);
                double average = average(acc, track.logs);

                if (average >= 60) {
                    cpuJSON.put("overload", true);
                } else {
                    cpuJSON.put("overload", false);
                }

                cpuJSON.put("peak", max);
                cpuJSON.put("logs", track.logs.stream().mapToDouble(Double::doubleValue).toArray());
                cpuJSON.put("lastUsage", track.usage);
                cpuJSON.put("average", average);
                output.put("cpu", cpuJSON);

                track.logs = new ArrayList<>();
            }
        };
        centralProcessingUnitScheduler.scheduleAtFixedRate(cpuCommand, 0, 1, TimeUnit.SECONDS);

        // RAM
        ScheduledExecutorService randomMemoryScheduler = Executors.newScheduledThreadPool(1);
        Runnable ramCommand = () -> {
            Output track = RandomMemory.track();
            if (track.logs.size() == DELIMITER) {
                double acc = 0;
                double max = max(track.logs);
                double average = average(acc, track.logs);

                if (average >= 80) {
                    ramJSON.put("overload", true);
                } else {
                    ramJSON.put("overload", false);
                }

                ramJSON.put("peak", max);
                ramJSON.put("logs", track.logs.stream().mapToDouble(Double::doubleValue).toArray());
                ramJSON.put("lastUsage", track.usage);
                ramJSON.put("average", average);
                output.put("ram", ramJSON);

                track.logs = new ArrayList<>();
            }
        };

        randomMemoryScheduler.scheduleAtFixedRate(ramCommand, 0, 1, TimeUnit.SECONDS);

        // STORAGE
        ScheduledExecutorService storageScheduler = Executors.newScheduledThreadPool(1);

        Runnable storageCommand = () -> {
            OutputSU track = StorageUnit.track();
            storageJSON.put("available", track.available);
            storageJSON.put("total", track.total);
            if ((track.available / track.total) >= 80) {
                storageJSON.put("overload", true);
            } else {
                storageJSON.put("overload", false);
            }
            output.put("storage", storageJSON);
        };

        storageScheduler.scheduleAtFixedRate(storageCommand, 0, 1, TimeUnit.SECONDS);

        return output;
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
        return accumulator / DELIMITER;
    }
}
