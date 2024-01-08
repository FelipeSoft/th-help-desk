package com.thsolucoes.helpdesk.hardware;

import com.thsolucoes.helpdesk.domain.CPU;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class CentralProcessingUnit {

    private static final SystemInfo si = new SystemInfo();
    private static final CentralProcessor processor = si.getHardware().getProcessor();

    private final static long[] prevTicks = new long[CentralProcessor.TickType.values().length];
    private static short minutesCounter = 0;

    private static double usageAverage = 0;

    public static void track() throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        CPU cpu = new CPU();

        Runnable task = () -> {
            double usage = getCpuUsage();
            if (minutesCounter == 12) {
                double average = (usageAverage / 12);
                cpu.setAverage(average);
                if (average >= 70) {
                    cpu.setOverload(true);
                }
                minutesCounter = 0;
                usageAverage = 0;
                return;
            }
            cpu.setUsage(usage);
            minutesCounter++;
            usageAverage += usage;
        };

        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    private static double getCpuUsage() {
        long[] ticks = processor.getSystemCpuLoadTicks();

        double usage = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        System.arraycopy(ticks, 0, prevTicks, 0, ticks.length);

        return usage;
    }

    /* private static double getCpuUsage() {
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
    } */
}
