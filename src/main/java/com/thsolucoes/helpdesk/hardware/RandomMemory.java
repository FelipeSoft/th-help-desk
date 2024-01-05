/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.hardware;

import java.text.DecimalFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import oshi.SystemInfo;

/**
 *
 * @author NTH05
 */
public class RandomMemory {
    
    private final static SystemInfo si = new SystemInfo();

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            DecimalFormat df = new DecimalFormat();
            double usage = getRandomMemoryUsage();
            double percentage = (usage / (si.getHardware().getMemory().getTotal() / 1e9)) * 100;
            
            System.out.println(percentage + "%");
            
            if (percentage >= 80) {
                System.out.println("RAM Overload!");
                System.out.println("RAM Usage Percentage: " + df.format(percentage) + "%");
            }
            
            System.out.println("Uso da RAM: " + df.format(usage));
        };
                
        scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }

    private static double getRandomMemoryUsage() {
        long total = si.getHardware().getMemory().getTotal();
        long available = si.getHardware().getMemory().getAvailable();

        long usage = total - available;
        return usage / 1e9;
    }
}
