package com.sucre.destiny.service.impl;

import com.sucre.destiny.common.ChineseCalendar;
import com.sucre.destiny.common.LunarTerm;
import com.sucre.destiny.common.lunarTosolar.Lunar;
import com.sucre.destiny.common.lunarTosolar.LunarSolar;
import com.sucre.destiny.common.lunarTosolar.Solar;
import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.enums.ResultCodeEnum;
import com.sucre.destiny.exception.BizException;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.service.IEightWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: ConvertToEightWord
 * @Package service
 * @Description: 将指定年月日转换到对应的八字对象，暂时还没考虑闰月的情况。
 * @date 2019-05-08 15:06
 */
@Service
public class ConvertToEightWord implements IEightWord {
    public final String[] JIA_ZI = {
            "甲子", "乙丑", "丙寅", "丁卯", "戊辰", "己巳", "庚午", "辛未", "壬申", "癸酉",
            "甲戌", "乙亥", "丙子", "丁丑", "戊寅", "己卯", "庚辰", "辛巳", "壬午", "癸未",
            "甲申", "乙酉", "丙戌", "丁亥", "戊子", "己丑", "庚寅", "辛卯", "壬辰", "癸巳",
            "甲午", "乙未", "丙申", "丁酉", "戊戌", "己亥", "庚子", "辛丑", "壬寅", "癸卯",
            "甲辰", "乙巳", "丙午", "丁未", "戊申", "己酉", "庚戌", "辛亥", "壬子", "癸丑",
            "甲寅", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥"
    };
    public final  String[] Gan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    public final  String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    @Autowired
    TermToEarth termToEarth;//


    public List<String> time2Eight(Calendar calendar) throws Exception {

        List<String> resultList = new ArrayList<>();
         /*Calendar calendar = Calendar.getInstance();
         calendar.set(1970, 10 - 1, 22, 4, 30, 10);*/
        //查看是否过了立春，如果没过依然按上一年算。
        boolean b = isCrossSpring(calendar);
        //年干支
        //1864年是甲子年，每隔六十年一个甲子
        int year = calendar.get(Calendar.YEAR);
        int idx = (b ? (year - 1864) : ((year - 1) - 1864)) % 60;//查看是否过了立春，如果没过依然按上一年算。
        //String yearGanZhi = JIA_ZI[idx];
        //System.out.println(yearGanZhi);
        resultList.add(String.valueOf(JIA_ZI[idx].charAt(0)));//年干
        resultList.add(String.valueOf(JIA_ZI[idx].charAt(1)));//年支

        //月支
        Long myTime = (calendar.getTimeInMillis() / 1000) * 1000;//日主的出生年月转换成毫秒，除一千是为了清除函数返回后面的三个随机数。
        String result = null;//接收月干的结果
        List<Map<String, String>> list = new ArrayList<>(); //接收日主所在年份的所有节气
        Map<String, String> map = new HashMap<>();//接收当前节气
        Map<String,String>lastMap=new HashMap<>();//接收最后一个节气，取当前交接节气跨年时用。
        //循环直接到 未交接的节气。
        while (result == null) {
            list = allTerm(year);//接收日主所在年份的所有节气

            //遍历所有节气
            for (int i = 0; i < list.size(); i++) {
                map = list.get(i);//接收当前节气
                Long termTime = TimeToStamp(map.get("date"));//把当前节气的具体时间转换成毫秒
                if (termTime >= myTime) {//把当前节气的毫秒 大于或者等于 日主出生年月，说明没交当前节气，月干返回上一个节气
                    map = i==0?lastMap:list.get(i - 1);//上一个节气
                    result = map.get("term");//把上一个节气的名称返回
                    break;
                }

            }
            lastMap=map;
            year++;//所在年的节气都过了，加1 在下一年找没交接的节气。
        }
        /**
         * 年上起月
         * 甲己之年丙作首，乙庚之岁戊为头，
         * 丙辛必定寻庚起，丁壬壬位顺行流，
         * 更有戊癸何方觅，甲寅之上好追求。
         */
        // TermToEarth termToEarth = new TermToEarth();
        //先把起点遁出来
        int monthIdx = (idx + 1) * 2;
        monthIdx = monthIdx == 10 ? 0 : monthIdx;
        result = termToEarth.termToMonthEarth(result);//把结果节气转换成对应的月份的地支
        //把起点跟月份加起来 得出月干
        // System.out.println(Gan[(monthIdx + Integer.parseInt(termToEarth.termToMonthEarth(result))-1) % 10]);
        //System.out.println(result);
        resultList.add(Gan[(monthIdx + Integer.parseInt(termToEarth.termToMonthEarth(result)) - 1) % 10]);//月干
        resultList.add(result);//月支

        //日干支
        /*求出和1900年1月31日0点0分0秒甲辰日相差的天数
         * 甲辰日是第四十天
         */
        Long t = -2206425943000L;//1900年1月31日0点0分0秒
        int offset = (int) ((myTime - t) / 86400000L);
        offset = (offset + 40) % 60;
        // String dayGanZhi = JIA_ZI[offset];
        //System.out.println(dayGanZhi);
        resultList.add(String.valueOf(JIA_ZI[offset].charAt(0)));//日干
        resultList.add(String.valueOf(JIA_ZI[offset].charAt(1)));//日支
        /**
         * 日上起时
         * 甲己还生甲，乙庚丙作初，
         * 丙辛从戊起，丁壬庚子居，
         * 戊癸何方发，壬子是真途。
         */
        //求得时辰的干支
        //先把起点遁出来
        offset = (offset % 5) * 2;
        int hour = (calendar.get(Calendar.HOUR_OF_DAY) + 25) % 24 / 2; //时辰的位置
        //用位置得到时干，时支。
        // System.out.println(Gan[(offset + hour) % 10]);
        // System.out.println(Zhi[hour]);
        resultList.add(Gan[(offset + hour) % 10]);//时干
        resultList.add(Zhi[hour]);//时支
        return resultList;
    }

    /**
     * 判断出生年月日时分秒，是否过了立春。
     *
     * @param birthDay
     * @return 等于或大于返回true.否则返回false
     */
    private  boolean isCrossSpring(Calendar birthDay) {

        Long springTime = TimeToStamp(springTime(birthDay.get(Calendar.YEAR)));
        Long myTime = (birthDay.getTimeInMillis() / 1000) * 1000;
        //System.out.println(brithDay.getTime().getTime());
        return myTime >= springTime ? true : false;
    }

    /**
     * 根据年份取立春的具体时间
     *
     * @param year
     * @return
     */
    private  String springTime(Integer year) {
        String springDay = "";
        LunarTerm lunarTerm = new LunarTerm();
        springDay = lunarTerm.getTimeByTerm((year - 1), "立春");
        System.out.println(springDay);
        //System.out.println(TimeToStamp(springDay));
        // lunarTerm.JQtest(year-1);
        return springDay;
    }

    /**
     * 返回指定年份的所有节气名字和具体时间
     *
     * @param year
     * @return {date= 1988-03-20 17:38:45, term=春分}
     */
    private  List<Map<String, String>> allTerm(Integer year) {
        LunarTerm lunarTerm = new LunarTerm();
        return lunarTerm.JQList(year - 1);
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
     *
     * @param time
     * @return
     */
    private  Long TimeToStamp(String time) {
        //System.out.println(time);
        //System.out.println(System.currentTimeMillis());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf2.parse(time);
            return date.getTime();

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return 0L;

    }

    @Override
    public PersonInfo time2Person(Boolean isLeap,Boolean isChinese,PersonDTO personDTO) {
        Calendar calendar = Calendar.getInstance();
        PersonInfo personInfo = new PersonInfo();
        ChineseCalendar chineseCalendar=null;
        if(isChinese) {
            if(!isLeap) {
                chineseCalendar = new ChineseCalendar(isChinese, personDTO.getYear(), personDTO.getMonth(), personDTO.getDay(), personDTO.getHour(), personDTO.getMinute(), personDTO.getSecond());
                calendar.set(chineseCalendar.get(ChineseCalendar.YEAR), chineseCalendar.get(ChineseCalendar.MONTH), chineseCalendar.get(ChineseCalendar.DATE), chineseCalendar.get(Calendar.HOUR_OF_DAY), chineseCalendar.get(ChineseCalendar.MINUTE), chineseCalendar.get(ChineseCalendar.SECOND));
            }else{
                //农历的闰月，先转换成公历，然后再转换成calendar对象
                LunarSolar lunarSolar = new LunarSolar();
                Lunar lunar=new Lunar();
                lunar.setLunarYear(personDTO.getYear());
                lunar.setLunarMonth(personDTO.getMonth());
                lunar.setLunarDay(personDTO.getDay());
                lunar.setIsleap(true);//闰月
                Solar new_solar = lunarSolar.LunarToSolar(lunar);//转换成公历
                //设置日主的出生年月。calendar的月份从0开始算，所以要减1
                calendar.set(new_solar.getSolarYear(), new_solar.getSolarMonth()-1, new_solar.getSolarDay(), personDTO.getHour(), personDTO.getMinute(),personDTO.getSecond());
               // chineseCalendar= new ChineseCalendar(calendar);
            }
        }else {
            //设置日主的出生年月。calendar的月份从0开始算，所以要减1
            calendar.set(personDTO.getYear(), personDTO.getMonth() - 1, personDTO.getDay(), personDTO.getHour(), personDTO.getMinute(), personDTO.getSecond());
           // chineseCalendar= new ChineseCalendar(calendar);
        }
        chineseCalendar= new ChineseCalendar(calendar);
        //开始装入返回数据。
        //BeanUtils.copyProperties(personDTO,personInfo);
        //装入昵称，阳历出生年月日等
        personInfo.setNick(personDTO.getNick());
        personInfo.setYear(calendar.get(Calendar.YEAR));
        personInfo.setMonth(calendar.get(Calendar.MONTH)+1);//月份从0开始
        personInfo.setDay(calendar.get(Calendar.DATE));
        personInfo.setHour(calendar.get(Calendar.HOUR));
        personInfo.setMinute(calendar.get(Calendar.MINUTE));
        personInfo.setSecond(calendar.get(Calendar.SECOND));
        //装入性别
        personInfo.setGender(personDTO.getGender());
        //装入对应的农历
        personInfo.setChineseYear(chineseCalendar.getChinese(ChineseCalendar.CHINESE_YEAR));
        personInfo.setChineseMonth(chineseCalendar.getChinese(ChineseCalendar.CHINESE_MONTH));
        personInfo.setChineseDay(chineseCalendar.getChinese(ChineseCalendar.CHINESE_DATE));

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("立春",springTime(personDTO.getYear()).trim());//取本年的立春时间

        personInfo.setComment(resultMap);

        //装入八字
        try {
            personInfo.setEightWord(time2Eight(calendar));
        } catch (Exception e) {
            throw new BizException(ResultCodeEnum.CONVERT_ERROR);
        }

        return personInfo;
    }
}
