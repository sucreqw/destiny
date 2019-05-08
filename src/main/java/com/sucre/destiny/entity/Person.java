package com.sucre.destiny.entity;

import java.util.Arrays;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: Person
 * @Package entity
 * @Description: 个人信息类，包括年月日，八字，昵称等。
 * @date 2019-05-08 14:28
 */
public class Person {

    String nick;//昵称

    int year;//阳历的出生年
    int month;//阳历的出生月
    int day;//阳历的出生日

    String chineseYear;//农历的出生年，如：己亥
    String chineseMonth;//农历的出生月
    String chineseDay;//农历的出生日，如：初二

    String chineseZodiac;//所属生肖

    String comment;//其它的一些信息，比如前一个节气。

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getChineseZodiac() {
        return chineseZodiac;
    }

    public void setChineseZodiac(String chineseZodiac) {
        this.chineseZodiac = chineseZodiac;
    }

    String[] eightWord={"年干","年支","月干","月支","日干","日支","时干","时支"};//对应的八字。

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    public String[] getEightWord() {
        return eightWord;
    }

    public void setEightWord(String[] eightWord) {
        this.eightWord = eightWord;
    }

   /* @Override
    public String toString() {
        return "Person{" +
                "nick='" + nick + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", chineseYear='" + chineseYear + '\'' +
                ", chineseMonth='" + chineseMonth + '\'' +
                ", chineseDay='" + chineseDay + '\'' +
                ", eightWord=" + Arrays.toString(eightWord) +
                '}';
    }*/
}

