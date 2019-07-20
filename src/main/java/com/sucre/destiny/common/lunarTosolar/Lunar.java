package com.sucre.destiny.common.lunarTosolar;

/**
 * Created by firer on 2017/8/18.
 */
public class Lunar {
    public int lunarYear;
    public int lunarMonth;
    public int lunarDay;
    public boolean isleap;
    public String toString(){
        return lunarYear+"-"+lunarMonth+"-"+lunarDay;
    }

    public int getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(int lunarYear) {
        this.lunarYear = lunarYear;
    }

    public int getLunarMonth() {
        return lunarMonth;
    }

    public void setLunarMonth(int lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    public int getLunarDay() {
        return lunarDay;
    }

    public void setLunarDay(int lunarDay) {
        this.lunarDay = lunarDay;
    }

    public boolean isIsleap() {
        return isleap;
    }

    public void setIsleap(boolean isleap) {
        this.isleap = isleap;
    }
}
