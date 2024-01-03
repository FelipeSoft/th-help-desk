package com.thsolucoes.helpdesk.hardware;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor.LogicalProcessor;
import oshi.software.os.OSProcess;

public class CentralProcessingUnit {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            double usage = getCpuUsage();
            System.out.println("CPU USAGE: " + usage + "%");
        };

        scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }

    private static double getCpuUsage() {
        String os = System.getProperty("os.name").toLowerCase();
        SystemInfo si = new SystemInfo();

        List<LogicalProcessor> logicalsProcessors = si.getHardware().getProcessor().getLogicalProcessors();
        short logicalsProcessorsCount = 0;

        for (LogicalProcessor logicalsProcessor : logicalsProcessors) {
            logicalsProcessorsCount++;
        }

        List<OSProcess> processes = si.getOperatingSystem().getProcesses();
        double usage = 0;

        for (OSProcess process : processes) {
            usage += process.getProcessCpuLoadBetweenTicks(process);
        }

        double compatibleUsageForOperationalSystems;

        if (os.contains("win")) {
            compatibleUsageForOperationalSystems = (usage / logicalsProcessorsCount) * 100;
        } else {
            compatibleUsageForOperationalSystems = usage * 100;
        }
        return compatibleUsageForOperationalSystems;
        //return si.getOperatingSystem().getProcess(0).getProcessCpuLoadCumulative() * 100;
    }
}
