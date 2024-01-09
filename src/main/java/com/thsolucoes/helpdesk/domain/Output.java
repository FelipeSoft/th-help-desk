package com.thsolucoes.helpdesk.domain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NTH05
 */
public class Output {
    public double usage = 0;
    public List<Double> logs = new ArrayList<>();
    public boolean overload = false;

    public Output() {
    }
    
    public void log(double logValue) {
        logs.add(logValue);
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    public void setOverload(boolean overload) {
        this.overload = overload;
    }
}
