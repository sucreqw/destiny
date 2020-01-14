package com.sucre.destiny.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2019-06-12
 */
@TableName("d_person")
public class DPersonDO implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 命造名称
     */
    @TableField("nick")
    private String nick;

    /**
     * 备注,目录记录立春时间
     */
    @TableField("comment")
    private String comment;

    /**
     * 创建人id
     */
    @TableField("create_id")
    private Integer createId;
    /**
     * 阳历年
     */
    @TableField("year")
    private Integer year;

    /**
     * 阳历月
     */
    @TableField("month")
    private Integer month;
    /**
     * 阳历日
     */
    @TableField("day")
    private Integer day;
    /**
     * 小时
     */
    @TableField("hour")
    private Integer hour;
    /**
     * 分钟
     */
    @TableField("minute")
    private Integer minute;
    /**
     * 秒
     */
    @TableField("second")
    private Integer second;
    /**
     * 农历日
     */
    @TableField("chinese_day")
    private String chineseDay;
    /**
     * 农历月
     */
    @TableField("chinese_month")
    private String chineseMonth;
    /**
     * 农历年
     */
    @TableField("chinese_year")
    private String chineseYear;
    /**
     * 生肖
     */
    @TableField("chinese_zodiac")
    private String chineseZodiac;
    /**
     * 八字
     */
    @TableField("eight_word")
    private String eightWord;
    /**
     * 是否农历
     */
    @TableField("is_chinese")
    private Integer isChinese;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getIsChinese() {
        return isChinese;
    }

    public void setIsChinese(Integer isChinese) {
        this.isChinese = isChinese;
    }

    public Integer getIsLeap() {
        return isLeap;
    }

    public void setIsLeap(Integer isLeap) {
        this.isLeap = isLeap;
    }

    /**
     * 是否农历
     */
    @TableField("is_leap")
    private Integer isLeap;
    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }



    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
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

    public String getChineseDay() {
        return chineseDay;
    }

    public void setChineseDay(String chineseDay) {
        this.chineseDay = chineseDay;
    }

    public String getChineseMonth() {
        return chineseMonth;
    }

    public void setChineseMonth(String chineseMonth) {
        this.chineseMonth = chineseMonth;
    }

    public String getChineseYear() {
        return chineseYear;
    }

    public void setChineseYear(String chineseYear) {
        this.chineseYear = chineseYear;
    }

    public String getChineseZodiac() {
        return chineseZodiac;
    }

    public void setChineseZodiac(String chineseZodiac) {
        this.chineseZodiac = chineseZodiac;
    }

    public String getEightWord() {
        return eightWord;
    }

    public void setEightWord(String eightWord) {
        this.eightWord = eightWord;
    }


    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
