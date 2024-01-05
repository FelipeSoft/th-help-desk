/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.hardware;

import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

/**
 *
 * @author NTH05
 */
public class StorageUnit {
    private final static SystemInfo si = new SystemInfo();
    
    public static void main(String[] args) {
        List<HWDiskStore> diskStores = si.getHardware().getDiskStores();
        double usage = 0;
        double current = 0;
        double total = 0;
        
        for (HWDiskStore disk : diskStores) {
            current += disk.getWriteBytes();
            total += disk.getSize();
            usage += total - current;
        }
        
        System.out.println("Armazenamento Total Disponível: " + (usage / 1e9) + " GHz");
    }
}
