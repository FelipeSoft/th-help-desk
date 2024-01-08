/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.gateways;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author NTH05
 */
public class HardwareCollectorGateway {

    public static void main(String[] args) {
        // CPU
        ScheduledExecutorService centralProcessingUnitScheduler = Executors.newScheduledThreadPool(1);
        Runnable cpu = () -> {

        };
        centralProcessingUnitScheduler.scheduleAtFixedRate(cpu, 0, 1, TimeUnit.SECONDS);

        // RAM
        ScheduledExecutorService randomMemoryScheduler = Executors.newScheduledThreadPool(1);
        Runnable ram = () -> {

        };
        randomMemoryScheduler.scheduleAtFixedRate(ram, 0, 1, TimeUnit.SECONDS);
        
        // STORAGE
        ScheduledExecutorService storageUnitScheduler = Executors.newScheduledThreadPool(1);
        Runnable storage = () -> {
            
        };
        storageUnitScheduler.scheduleAtFixedRate(storage, 0, 1, TimeUnit.DAYS);
    }
}
