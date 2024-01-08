package com.thsolucoes.helpdesk.hardware;

import com.thsolucoes.helpdesk.domain.CPU;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

// Lembrar de ajustar as informações de retorno do método track(), ele deve retornar dados da classe CPU;
// Não está atribuindo a média de uso da cpu na classe CPU;
public class CentralProcessingUnit {

    private static final SystemInfo si = new SystemInfo();
    private static final CentralProcessor processor = si.getHardware().getProcessor();

    private final static long[] prevTicks = new long[CentralProcessor.TickType.values().length];
    private static double usage;

    public static double usageAverage = 0;
    public static short minutesCounter = 0;
    public static CPU cpu = new CPU();

    public static CPU track() {
        usage = getCpuUsage();
        if (minutesCounter == 12) {
            double average = (usageAverage / 12);
            cpu.setAverage(average);
            if (Double.parseDouble(cpu.average) >= 70) {
                cpu.setOverload(true);
            } else {
                cpu.setOverload(false);
            }
        } else {
            load(); 
            cpu.setAverage(usageAverage / 12);
        }
        System.out.println("---------Count---------");
        System.out.println(minutesCounter);
        return cpu;
    }

    public static void load() {
        cpu.setUsage(usage);
        minutesCounter++;
        usageAverage += usage;
    }

    private static double getCpuUsage() {
        long[] ticks = processor.getSystemCpuLoadTicks();

        double usageFromCpuUsageMethod = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        System.arraycopy(ticks, 0, prevTicks, 0, ticks.length);

        return usageFromCpuUsageMethod;
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
