/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.hardware;

import com.thsolucoes.helpdesk.domain.Output;
import oshi.SystemInfo;

/**
 *
 * @author NTH05
 */
public class RandomMemory {

    private final static SystemInfo si = new SystemInfo();
    private final static Output output = new Output();

    public static Output track() {
        double usage = getRandomMemoryUsage();
        double percentage = (usage / (si.getHardware().getMemory().getTotal() / 1e9)) * 100;

        output.log(usage);
        
        if (percentage >= 80) {
            output.setOverload(true);
        }
       
        output.setUsage(usage);
        return output;
    }

    private static double getRandomMemoryUsage() {
        long total = si.getHardware().getMemory().getTotal();
        long available = si.getHardware().getMemory().getAvailable();

        long usage = total - available;
        return usage / 1e9;
    }
}
