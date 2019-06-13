package com.sucre.destiny.info;

import java.util.List;
import java.util.Map;

public class PersonInfo {
    String nick;//昵称

    private Integer year;//阳历的出生年
    private Integer month;//阳历的出生月
    private Integer day;//阳历的出生日
    private Integer hour;
    private Integer minute;
    private Integer second;

    String chineseYear;//农历的出生年，如：己亥
    String chineseMonth;//农历的出生月
    String chineseDay;//农历的出生日，如：初二
    String chineseZodiac;//所属生肖
    Map<String,Object> comment;//其它的一些信息，比如前一个节气。

    List<String> eightWord;//对应的八字。

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public String getChineseYear() {
        return chineseYear;
    }

    public void setChineseYear(String chineseYear) {
        this.chineseYear = chineseYear;
    }

    public String getChineseMonth() {
        return chineseMonth;
    }

    public void setChineseMonth(String chineseMonth) {
        this.chineseMonth = chineseMonth;
    }

    public String getChineseDay() {
        return chineseDay;
    }

    public void setChineseDay(String chineseDay) {
        this.chineseDay = chineseDay;
    }

    public String getChineseZodiac() {
        return chineseZodiac;
    }

    public void setChineseZodiac(String chineseZodiac) {
        this.chineseZodiac = chineseZodiac;
    }

    public Map<String, Object> getComment() {
        return comment;
    }

    public void setComment(Map<String, Object> comment) {
        this.comment = comment;
    }

    public List<String> getEightWord() {
        return eightWord;
    }

    public void setEightWord(List<String> eightWord) {
        this.eightWord = eightWord;
    }
}
