package com.sucre.destiny.service;

import com.sucre.destiny.common.ChineseCalendar;
import com.sucre.destiny.common.LunarTerm;
import com.sucre.destiny.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: ConvertToEightWord
 * @Package service
 * @Description: 将指定年月日转换到对应的八字对象
 * @date 2019-05-08 15:06
 */
@Service
public class ConvertToEightWord {


    @Autowired
    TermToEarth termToEarth;


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
    public Person timeToEight(boolean isChinese, int year, int month, int day, int hour, int min, int sec, String nick) {
        //命例对象
        Person person = null;
        ChineseCalendar chineseCalendar = new ChineseCalendar(isChinese //是否为农历日期
                , year //出生年份
                , isChinese == true ? month : month - 1 //出生月份，因为calendar类月份从0开始算，可以要减1，农历则不用减。
                , day//出生日
                , hour//小时
                , min//分钟
                , sec//秒
        );
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
        //先看看立春是什么时候，然后确定年的天干地支：
        isCrossSpring(chineseCalendar);

        //先取得最近的节气时间，看看交的是什么月份。//如果出生日 交了节气 直接按当前算。
        String TermNames = chineseCalendar.getChinese(ChineseCalendar.CHINESE_SECTIONAL_TERM);//取当前所在月的节
        int TermDay = chineseCalendar.get(ChineseCalendar.CHINESE_SECTIONAL_TERM);//正式交节 的阳历日。
        //判断当前日主所在的阳历的月份和日  是否过了节气， 过了就是当前月，没过就是上一个月。

        //出生日没交节气日，按上一个月算。
        TermNames = person.getDay() < TermDay ?
                chineseCalendar.getChinese(ChineseCalendar.CHINESE_PREVIOUS_SECTIONAL_TERM) :
                //出生日交了节气，就直接按当月算。
                TermNames;
        //如果出生日刚好在节气日，要特别注意，提醒手动查万年历看看交了节气没有
        if (person.getDay() == TermDay) {
            person.setComment("特别时间，出生日刚好交接节气，请手动确认是否已经交了节气。");
        }


        String monthEarth = termToEarth.termToMonthEarth(TermNames);

        System.out.println(monthEarth);
        return person;
    }

    private Map<Integer, String> isCrossSpring(ChineseCalendar chineseCalendar) {
        Map<Integer, String> result = new HashMap<>();
        ChineseCalendar spring = getSpringDate(chineseCalendar.get(ChineseCalendar.YEAR));
        //先把两个对象转换成timestamp yyyy-MM-dd HH:mm:ss
        long targetTimestamp =calendarToLong(chineseCalendar);
        //立春时间
        long springTimestamp=calendarToLong(spring);
        //判断两个对象 是否交接了立春。
        System.out.println(targetTimestamp +"|" +springTimestamp);

        return result;
    }

    private long calendarToLong(ChineseCalendar chineseCalendar){

        return TimeToStamp(chineseCalendar.get(ChineseCalendar.YEAR)
                + "-"
                + (chineseCalendar.get(ChineseCalendar.MONTH) + 1)
                + "-"
                + chineseCalendar.get(ChineseCalendar.DATE)
                + " "
                + chineseCalendar.get(Calendar.HOUR_OF_DAY)
                + ":"
                + chineseCalendar.get(Calendar.MINUTE)
                + ":"
                + chineseCalendar.get(Calendar.SECOND)
        );
    }


    /**
     * 循环取出立春的年月日，返回一个calender对象。用以跟当前对象比较，得出年柱。
     *
     * @param year 出生年 阳历
     * @return
     */
    private ChineseCalendar getSpringDate(int year) {
        //接收当前月份节气
        String springDay = "";
        //建立取节气的common类
        LunarTerm lunarTerm = new LunarTerm();
        //取得立春的具体时间。
        System.out.println(year);
        springDay = lunarTerm.getTimeByTerm((year-1) , "立春");

        ChineseCalendar chineseCalendar = new ChineseCalendar(StringToDate(springDay));
        return chineseCalendar;
    }


    /**
     * 把LunarTerm 取到的节气时间转换成date类型，重新加载到一个ChineseCalendar对象里。
     *
     * @param time
     * @return
     */
    private Date StringToDate(String time) {

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf2.parse(time);
            return date;
            //String stamp=String.valueOf(date.getTime());

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * 把节气取回来的时间转换成long 的时间戳。
     * @param time
     * @return
     */
    private long TimeToStamp(String time) {
        //System.out.println(time);
        //System.out.println(System.currentTimeMillis());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf2.parse(time);
            return date.getTime();

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return -1;

    }
}
