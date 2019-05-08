package com.sucre.destiny.service;

import com.sucre.destiny.common.ChineseCalendar;
import com.sucre.destiny.entity.Person;
import org.springframework.stereotype.Service;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: ConvertToEightWord
 * @Package service
 * @Description: 将指定年月日转换到对应的八字对象
 * @date 2019-05-08 15:06
 */
@Service
public class ConvertToEightWord {

    //命例对象
    Person person = null;

    /**
     * 根据出生年月日返回具体的八字
     *
     * @param isChinese 是否为农历
     * @param year      出生年份
     * @param month     出生月份
     * @param day       出生日
     * @param nick      昵称
     * @return
     */
    public Person timeToEight(boolean isChinese, int year, int month, int day, String nick) {
        ChineseCalendar chineseCalendar = new ChineseCalendar(isChinese //是否为农历日期
                , year //出生年份
                , isChinese == true ? month : month - 1 //出生月份，因为calendar类月份从0开始算，可以要减1，农历则不用减。
                , day);//出生日
        person = new Person(); //转换成功后返回的对象


        person.setNick(nick);//添加昵称
        person.setYear(chineseCalendar.get(ChineseCalendar.CHINESE_YEAR));//添加阳历年份
        person.setMonth(chineseCalendar.get(ChineseCalendar.MONTH) + 1);//添加阳历月份
        person.setDay(chineseCalendar.get(ChineseCalendar.DATE));//添加阳历日

        person.setChineseYear(chineseCalendar.getChinese(ChineseCalendar.CHINESE_YEAR));//添加农历年：己亥
        person.setChineseMonth(chineseCalendar.getChinese(ChineseCalendar.CHINESE_MONTH));//添加农历月
        person.setChineseDay(chineseCalendar.getChinese(ChineseCalendar.CHINESE_DATE));//添加农历日：初二

        person.setChineseZodiac(chineseCalendar.getChinese(ChineseCalendar.CHINESE_ZODIAC));//添加所属十二生肖

        //计算天十地支：
        //先取得最近的节气时间，看看交的是什么月份。
        return person;
    }
}
