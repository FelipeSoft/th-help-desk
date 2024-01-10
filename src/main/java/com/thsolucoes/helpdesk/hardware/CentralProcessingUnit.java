package com.thsolucoes.helpdesk.hardware;

import com.thsolucoes.helpdesk.domain.Output;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import java.util.List;

// Lembrar de ajustar as informações de retorno do método track(), ele deve retornar dados da classe CPU;
// Não está atribuindo a média de uso da cpu na classe CPU;
public class CentralProcessingUnit {
    private static final SystemInfo si = new SystemInfo();
    private static final CentralProcessor processor = si.getHardware().getProcessor();

    private final static long[] prevTicks = new long[CentralProcessor.TickType.values().length];
    private static double usage;

    public static short minutesCounter = 0;
    public static Output output = new Output();

    public static Output track() {
        usage = getCpuUsage();
        output.setUsage(usage);
        output.log(usage); 
        return output;
    }

    public static List<Double> average() {
        return output.logs;
    }

    public static void load() {
        output.setUsage(usage);
        minutesCounter++;
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
