package org.thehellnet.mobile.smlgrdroid.service;

import java.io.Serializable;

/**
 * Created by sardylan on 14/05/15.
 */
public class LiveData implements Serializable {
    int power;
    float todayProd;
    float todayMax;
    float todayVoltage;
    int todayTemp;
    float todayFrequency;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public float getTodayProd() {
        return todayProd;
    }

    public void setTodayProd(float todayProd) {
        this.todayProd = todayProd;
    }

    public float getTodayMax() {
        return todayMax;
    }

    public void setTodayMax(float todayMax) {
        this.todayMax = todayMax;
    }

    public float getTodayVoltage() {
        return todayVoltage;
    }

    public void setTodayVoltage(float todayVoltage) {
        this.todayVoltage = todayVoltage;
    }

    public int getTodayTemp() {
        return todayTemp;
    }

    public void setTodayTemp(int todayTemp) {
        this.todayTemp = todayTemp;
    }

    public float getTodayFrequency() {
        return todayFrequency;
    }

    public void setTodayFrequency(float todayFrequency) {
        this.todayFrequency = todayFrequency;
    }
}
