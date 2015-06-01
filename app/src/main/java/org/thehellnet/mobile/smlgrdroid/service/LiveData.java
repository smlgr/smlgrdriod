package org.thehellnet.mobile.smlgrdroid.service;

import java.io.Serializable;

/**
 * Created by sardylan on 14/05/15.
 */
public class LiveData implements Serializable {
    private int ac_power;
    private int ac_voltage;
    private int ac_current;
    private int ac_frequency;
    private int dc1_voltage;
    private int dc1_current;
    private int dc2_voltage;
    private int dc2_current;
    private int temperature;
    private int production;

    public int getAc_power() {
        return ac_power;
    }

    public void setAc_power(int ac_power) {
        this.ac_power = ac_power;
    }

    public int getAc_voltage() {
        return ac_voltage;
    }

    public void setAc_voltage(int ac_voltage) {
        this.ac_voltage = ac_voltage;
    }

    public int getAc_current() {
        return ac_current;
    }

    public void setAc_current(int ac_current) {
        this.ac_current = ac_current;
    }

    public int getAc_frequency() {
        return ac_frequency;
    }

    public void setAc_frequency(int ac_frequency) {
        this.ac_frequency = ac_frequency;
    }

    public int getDc1_voltage() {
        return dc1_voltage;
    }

    public void setDc1_voltage(int dc1_voltage) {
        this.dc1_voltage = dc1_voltage;
    }

    public int getDc1_current() {
        return dc1_current;
    }

    public void setDc1_current(int dc1_current) {
        this.dc1_current = dc1_current;
    }

    public int getDc2_voltage() {
        return dc2_voltage;
    }

    public void setDc2_voltage(int dc2_voltage) {
        this.dc2_voltage = dc2_voltage;
    }

    public int getDc2_current() {
        return dc2_current;
    }

    public void setDc2_current(int dc2_current) {
        this.dc2_current = dc2_current;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }
}
