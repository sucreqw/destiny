package com.sucre.destiny.common.lunarTosolar;


/**
 * Created by firer on 2017/8/18.
 */
public class Solar {
    public int solarYear;
    public int solarMonth;
    public int solarDay;
    public String toString(){
        return solarYear+"-"+solarMonth+"-"+solarDay;
    }

    public int getSolarYear() {
        return solarYear;
    }

    public void setSolarYear(int solarYear) {
        this.solarYear = solarYear;
    }

    public int getSolarMonth() {
        return solarMonth;
    }

    public void setSolarMonth(int solarMonth) {
        this.solarMonth = solarMonth;
    }

    public int getSolarDay() {
        return solarDay;
    }

    public void setSolarDay(int solarDay) {
        this.solarDay = solarDay;
    }
}
