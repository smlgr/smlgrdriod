package org.thehellnet.mobile.smlgrdroid.service;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by sardylan on 14/05/15.
 */
public class OnceData implements Serializable {
    int max;
    DateTime todayStart;
    DateTime todayStop;
    float yesterdayProd;
    float yesterdayMax;
    DateTime yesterdayStart;
    DateTime yesterdayStop;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public DateTime getTodayStart() {
        return todayStart;
    }

    public void setTodayStart(DateTime todayStart) {
        this.todayStart = todayStart;
    }

    public DateTime getTodayStop() {
        return todayStop;
    }

    public void setTodayStop(DateTime todayStop) {
        this.todayStop = todayStop;
    }

    public float getYesterdayProd() {
        return yesterdayProd;
    }

    public void setYesterdayProd(float yesterdayProd) {
        this.yesterdayProd = yesterdayProd;
    }

    public float getYesterdayMax() {
        return yesterdayMax;
    }

    public void setYesterdayMax(float yesterdayMax) {
        this.yesterdayMax = yesterdayMax;
    }

    public DateTime getYesterdayStart() {
        return yesterdayStart;
    }

    public void setYesterdayStart(DateTime yesterdayStart) {
        this.yesterdayStart = yesterdayStart;
    }

    public DateTime getYesterdayStop() {
        return yesterdayStop;
    }

    public void setYesterdayStop(DateTime yesterdayStop) {
        this.yesterdayStop = yesterdayStop;
    }
}
