package com.sucre.destiny.info;

import java.util.List;

public class YearFateInfo {
    Integer ageYear;
    Integer ageMonth;
    Integer ageDay;
    List<Integer> bigFateAge;
    List<Integer> bigFateYear;
    List<String> bigFateGen;
    List<String> bigFateZhi;

    //上运的年月日，公历。
    Integer fateYear;
    Integer fateMonth;
    Integer fateDay;
    String fateYearDetail;

    //小运
    List<String> littleFateGen;
    List<String> littleFateZhi;
    //流年
    List<Integer> flowYear;

    public Integer getAgeYear() {
        return ageYear;
    }

    public void setAgeYear(Integer ageYear) {
        this.ageYear = ageYear;
    }

    public Integer getAgeMonth() {
        return ageMonth;
    }

    public void setAgeMonth(Integer ageMonth) {
        this.ageMonth = ageMonth;
    }

    public Integer getAgeDay() {
        return ageDay;
    }

    public void setAgeDay(Integer ageDay) {
        this.ageDay = ageDay;
    }

    public List<Integer> getBigFateAge() {
        return bigFateAge;
    }

    public void setBigFateAge(List<Integer> bigFateAge) {
        this.bigFateAge = bigFateAge;
    }

    public List<Integer> getBigFateYear() {
        return bigFateYear;
    }

    public void setBigFateYear(List<Integer> bigFateYear) {
        this.bigFateYear = bigFateYear;
    }

    public List<String> getBigFateGen() {
        return bigFateGen;
    }

    public void setBigFateGen(List<String> bigFateGen) {
        this.bigFateGen = bigFateGen;
    }

    public List<String> getBigFateZhi() {
        return bigFateZhi;
    }

    public void setBigFateZhi(List<String> bigFateZhi) {
        this.bigFateZhi = bigFateZhi;
    }

    public Integer getFateYear() {
        return fateYear;
    }

    public void setFateYear(Integer fateYear) {
        this.fateYear = fateYear;
    }

    public Integer getFateMonth() {
        return fateMonth;
    }

    public void setFateMonth(Integer fateMonth) {
        this.fateMonth = fateMonth;
    }

    public Integer getFateDay() {
        return fateDay;
    }

    public void setFateDay(Integer fateDay) {
        this.fateDay = fateDay;
    }

    public String getFateYearDetail() {
        return fateYearDetail;
    }

    public void setFateYearDetail(String fateYearDetail) {
        this.fateYearDetail = fateYearDetail;
    }

    public List<String> getLittleFateGen() {
        return littleFateGen;
    }

    public void setLittleFateGen(List<String> littleFateGen) {
        this.littleFateGen = littleFateGen;
    }

    public List<String> getLittleFateZhi() {
        return littleFateZhi;
    }

    public void setLittleFateZhi(List<String> littleFateZhi) {
        this.littleFateZhi = littleFateZhi;
    }

    public List<Integer> getFlowYear() {
        return flowYear;
    }

    public void setFlowYear(List<Integer> flowYear) {
        this.flowYear = flowYear;
    }
}
