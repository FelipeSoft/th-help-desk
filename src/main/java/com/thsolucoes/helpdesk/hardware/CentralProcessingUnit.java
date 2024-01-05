package com.thsolucoes.helpdesk.hardware;

import java.text.DecimalFormat;
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

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            double usage = getCpuUsage();
            DecimalFormat df = new DecimalFormat("#0,00");
            if (minutesCounter == 12) {
                double average = (usageAverage / 12);
                System.out.println("Média de Uso da CPU: " + df.format((usageAverage / 12) * 100) + "%");
                if (average >= 70) {
                    System.out.println("CPU Overload!");
                }
                minutesCounter = 0;
                usageAverage = 0;
                return;
            }
            System.out.println("CPU USAGE: " + df.format(usage * 100) + "%");
            minutesCounter++;
            usageAverage += usage;
        };

        scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
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
