/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thsolucoes.helpdesk.domain;

import java.text.DecimalFormat;

/**
 *
 * @author NTH05
 */
public class CPU {

    public String usage = "0";
    public boolean overload = false;
    public String average = "0";

    public CPU() {}
    
    public void setUsage(double usage) {
        this.usage = String.valueOf(usage);
    }
    
    public void setOverload(boolean overload) {
        this.overload = overload;
    }
    
    public void setAverage(double average) {
        this.average = String.valueOf(average);
    }

    private void formatUsageOfCentralProcessingUnit() {
        DecimalFormat df = new DecimalFormat("#0,00");
        double formated = Double.parseDouble(this.usage) * 100;
        this.usage = df.format(formated) + " %";
    }

    private void formatAverageUsageOfCentralProcessingUnit() {
        DecimalFormat df = new DecimalFormat("#0,00");
        double formated = Double.parseDouble(this.average);
        if (formated != 0) {
            this.average = df.format(formated * 100) + " %";
        }
        this.average = "0,00 %";
    }
}
