package com.sucre.destiny.info;

import java.util.HashMap;

public class TenGodInfo {
    //干的十神
    private String yearGenGod;
    private String monthGenGod;
    private String hourGenGod;
    //地支藏干
    private String[] hideYearGen;
    private String[] hideMonthGen;
    private String[] hideDayGen;
    private String[] hideHourGen;
    //地支藏干对应的十神
    private String[] hideYearGod;
    private String[] hideMonthGod;
    private String[] hideDayGod;
    private String[] hideHourGod;
    private String[] allFive;

    public String getYearGenGod() {
        return yearGenGod;
    }

    public void setYearGenGod(String yearGenGod) {
        this.yearGenGod = yearGenGod;
    }

    public String getMonthGenGod() {
        return monthGenGod;
    }

    public void setMonthGenGod(String monthGenGod) {
        this.monthGenGod = monthGenGod;
    }

    public String getHourGenGod() {
        return hourGenGod;
    }

    public void setHourGenGod(String hourGenGod) {
        this.hourGenGod = hourGenGod;
    }

    public String[] getHideYearGen() {
        return hideYearGen;
    }

    public void setHideYearGen(String[] hideYearGen) {
        this.hideYearGen = hideYearGen;
    }

    public String[] getHideMonthGen() {
        return hideMonthGen;
    }

    public void setHideMonthGen(String[] hideMonthGen) {
        this.hideMonthGen = hideMonthGen;
    }

    public String[] getHideDayGen() {
        return hideDayGen;
    }

    public void setHideDayGen(String[] hideDayGen) {
        this.hideDayGen = hideDayGen;
    }

    public String[] getHideHourGen() {
        return hideHourGen;
    }

    public void setHideHourGen(String[] hideHourGen) {
        this.hideHourGen = hideHourGen;
    }

    public String[] getHideYearGod() {
        return hideYearGod;
    }

    public void setHideYearGod(String[] hideYearGod) {
        this.hideYearGod = hideYearGod;
    }

    public String[] getHideMonthGod() {
        return hideMonthGod;
    }

    public void setHideMonthGod(String[] hideMonthGod) {
        this.hideMonthGod = hideMonthGod;
    }

    public String[] getHideDayGod() {
        return hideDayGod;
    }

    public void setHideDayGod(String[] hideDayGod) {
        this.hideDayGod = hideDayGod;
    }

    public String[] getHideHourGod() {
        return hideHourGod;
    }

    public String[] getAllFive() {
        return allFive;
    }

    public void setAllFive(String[] allFive) {
        this.allFive = allFive;
    }

    public void setHideHourGod(String[] hideHourGod) {
        this.hideHourGod = hideHourGod;
    }

    public void setByIndex(int index, Object data) {
        switch (index) {
            //年干
            case 0:
                this.yearGenGod = (String) data;
                break;
            //月干
            case 1:
                this.monthGenGod = (String) data;
                break;
            //日主
            case 2:
                break;
            //时干
            case 3:
                this.hourGenGod = (String) data;
                break;
            //年支 藏干
            case 4:
                this.hideYearGen = (String[]) data;
                break;
            //年支藏干对应十神
            case 5:
                this.hideYearGod = (String[]) data;
                break;
            //月支 藏干
            case 6:
                this.hideMonthGen = (String[]) data;
                break;
            //月支藏干对应十神
            case 7:
                this.hideMonthGod = (String[]) data;
                break;
            //日支 藏干
            case 8:
                this.hideDayGen = (String[]) data;
                break;
            //日支藏干对应十神
            case 9:
                this.hideDayGod = (String[]) data;
                break;
            //时支藏干
            case 10:
                this.hideHourGen = (String[]) data;
                break;
            //时支藏干对应十神
            case 11:
                this.hideHourGod = (String[]) data;
                break;
            //八字对应的五行
            case 12:
                this.allFive = (String[]) data;
                break;
        }
    }
}
