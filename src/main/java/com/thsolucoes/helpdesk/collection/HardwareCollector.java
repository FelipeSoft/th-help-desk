/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.collection;

import oshi.SystemInfo;

public class HardwareCollector {

    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        System.out.println("Temperatura: " + si.getHardware().getSensors().getCpuTemperature());
        // System.out.println("Armazenamento: " + si.getHardware().getDiskStores());;;
        // System.out.println("Sistema de Computador: " + si.getHardware().getComputerSystem());
        // System.out.println("Displays: " + si.getHardware().getDisplays());
        // System.out.println("Cartões Gráficos: " + si.getHardware().getGraphicsCards());
        // System.out.println("Grupos de Volumes Lógicos: " + si.getHardware().getLogicalVolumeGroups());
        // System.out.println("Memória RAM: " + si.getHardware().getMemory());
        // System.out.println("Network IFs: " + si.getHardware().getNetworkIFs());
        // System.out.println("Recursos de Energia: " + si.getHardware().getPowerSources());
        // System.out.println("Processador: " + si.getHardware().getProcessor());
        // System.out.println("Sensores: " + si.getHardware().getSensors());
        // System.out.println("Cartões de Som: " + si.getHardware().getSoundCards());
        // System.out.println("Dispositivos USB: " + si.getHardware().getUsbDevices(true))
        // System.out.println("Família: " + si.getOperatingSystem().getFamily());
        // System.out.println("Fabricante: " + si.getOperatingSystem().getManufacturer());
        // System.out.println("Arquitetura em Bits: " + si.getOperatingSystem().getBitness());
        // System.out.println("Processo Atual: " + si.getOperatingSystem().getCurrentProcess());
        // System.out.println("Thread Atual: " + si.getOperatingSystem().getCurrentThread());
        // System.out.println("Janelas do Desktop: " + si.getOperatingSystem().getDesktopWindows(true));
        // System.out.println("Sistema de Arquivos: " + si.getOperatingSystem().getFileSystem());
        // System.out.println("Estatísticas de Protocolos de Internet: " + si.getOperatingSystem().getInternetProtocolStats());
        // System.out.println("Parâmetros de Rede " + si.getOperatingSystem().getNetworkParams());
        // System.out.println("Processo: " + si.getOperatingSystem().getProcess(0));
        // System.out.println("Quantidade de Processos: " + si.getOperatingSystem().getProcessCount());
        // System.out.println("ID do Processo: " + si.getOperatingSystem().getProcessId());
        // System.out.println("Sessões: " + si.getOperatingSystem().getSessions());
        // System.out.println("Tempo de Boot do Sistema: " + si.getOperatingSystem().getSystemBootTime());
        // System.out.println("Tempo de Ativação do Sistema: " + si.getOperatingSystem().getSystemUptime());
        // System.out.println("Quantidade de Threads: " + si.getOperatingSystem().getThreadCount());
        // System.out.println("ID da Thread: " + si.getOperatingSystem().getThreadId());
        // System.out.println("Versão: " + si.getOperatingSystem().getVersionInfo());
    }
}
