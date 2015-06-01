package org.thehellnet.mobile.smlgrdroid.service;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by sardylan on 14/05/15.
 */
public class OnceData implements Serializable {
    int maxPower;
    int todayMaxProd;
    long todayMaxTime;
    long todayStart;
    long todayStop;

    int yesterdayProd;
    int yesterdayMaxProd;
    long yesterdayMaxTime;
    long yesterdayStart;
    long yesterdayStop;

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getTodayMaxProd() {
        return todayMaxProd;
    }

    public void setTodayMaxProd(int todayMaxProd) {
        this.todayMaxProd = todayMaxProd;
    }

    public long getTodayMaxTime() {
        return todayMaxTime;
    }

    public void setTodayMaxTime(long todayMaxTime) {
        this.todayMaxTime = todayMaxTime;
    }

    public long getTodayStart() {
        return todayStart;
    }

    public void setTodayStart(long todayStart) {
        this.todayStart = todayStart;
    }

    public long getTodayStop() {
        return todayStop;
    }

    public void setTodayStop(long todayStop) {
        this.todayStop = todayStop;
    }

    public int getYesterdayProd() {
        return yesterdayProd;
    }

    public void setYesterdayProd(int yesterdayProd) {
        this.yesterdayProd = yesterdayProd;
    }

    public int getYesterdayMaxProd() {
        return yesterdayMaxProd;
    }

    public void setYesterdayMaxProd(int yesterdayMaxProd) {
        this.yesterdayMaxProd = yesterdayMaxProd;
    }

    public long getYesterdayMaxTime() {
        return yesterdayMaxTime;
    }

    public void setYesterdayMaxTime(long yesterdayMaxTime) {
        this.yesterdayMaxTime = yesterdayMaxTime;
    }

    public long getYesterdayStart() {
        return yesterdayStart;
    }

    public void setYesterdayStart(long yesterdayStart) {
        this.yesterdayStart = yesterdayStart;
    }

    public long getYesterdayStop() {
        return yesterdayStop;
    }

    public void setYesterdayStop(long yesterdayStop) {
        this.yesterdayStop = yesterdayStop;
    }
}
