package com.sucre.destiny.service.impl;

import com.sucre.destiny.common.LunarTerm;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.info.YearFateInfo;
import com.sucre.destiny.service.IYearFateService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 凡推大运者，始行之岁数，俱从所生之日时起。
 看年干，阳年生男，阴年生女则顺行，数至未来节日之时。
 看年干，阴年生男，阳年生女则逆行，数至已往节日之时。
 遇节而止，得足数三日，为一岁，三十日为十岁。
 余一日，作四月，三个时辰作一个月，一小时作五天，十二分钟为一天。
 以月柱顺逆推之。
 */

@Service
public class YearFateImpl implements IYearFateService {

    @Autowired
    TermToEarth termToEarth;//

    public final String[] Gen = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    public final String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    @Override
    public YearFateInfo bigFate(PersonInfo personInfo) {
        YearFateInfo yearFateInfo=new YearFateInfo();
        //取出生年份
        int year=personInfo.getYear();
        //用来装大运的结果
        List<List> result;
        //装入八字
        List<String> eight = personInfo.getEightWord();
        //先判断阴阳日主,然后再根据男女来决定顺逆。
        int me = indexOfGen(eight.get(0));//取出生年份天干位置，13579为阳，246810为阴
        int fORb;

        if ((me + 1) % 2 == 0) {
            //阴日主   ，判断男女。0为女
            if (personInfo.getGender() == 0) {
                //阴女，顺行。
                fORb = 0;
                //result = getBigFate(indexOfGen(eight.get(2)), indexOfZhi(eight.get(3)),0);

            } else {
                //阴男 逆行。
                fORb = 1;
            }
        } else {
            //阳日主，判断男女。0为女
            if (personInfo.getGender() == 0) {
                //阳女，逆行。
                fORb = 1;
            } else {
                //阳男 顺行。
                fORb = 0;
                //result = getBigFate(indexOfGen(eight.get(2)), indexOfZhi(eight.get(3)),0);
            }
        }
        //循环得到十个大运
        result = getBigFate(indexOfGen(eight.get(2)), indexOfZhi(eight.get(3)), fORb);
        //得到上运时间。
        List<Integer> ymdOfFate=yearOfFate(personInfo, fORb);
        //把得到的上运时间（年，月，日）加上日主的出生年月日，得到实际的上运时间。

        Calendar calendar=Calendar.getInstance();
        calendar.set(personInfo.getYear(), personInfo.getMonth() - 1, personInfo.getDay(), personInfo.getHour(), personInfo.getMinute(), personInfo.getSecond());
        //日主加上运岁数，年。
        calendar.add(Calendar.YEAR,ymdOfFate.get(0));
        //日主加上运月数。
        calendar.add(Calendar.MONTH,ymdOfFate.get(1));
        //日主加上运的日数。
        calendar.add(Calendar.DATE,ymdOfFate.get(2));

        //装入info对象返回。
        yearFateInfo.setBigFateGen(result.get(0));
        yearFateInfo.setBigFateZhi(result.get(1));

        //开始上运的年，月，日。
        yearFateInfo.setFateYearDetail("公历");
        yearFateInfo.setFateYear(calendar.get(Calendar.YEAR));
        yearFateInfo.setFateMonth(calendar.get(Calendar.MONTH)+1);//有份从0开始，所以要加1.
        yearFateInfo.setFateDay(calendar.get(Calendar.DATE));


        //上运的 岁，月，日
        yearFateInfo.setAgeYear(ymdOfFate.get(0));
        yearFateInfo.setAgeMonth(ymdOfFate.get(1));
        yearFateInfo.setAgeDay(ymdOfFate.get(2));
        //装入多少岁转大运。
        List<Integer> temp=new ArrayList<>();
        for(int i=0;i<10;i++){
            temp.add((calendar.get(Calendar.YEAR)-year)+(i*10));
        }
        yearFateInfo.setBigFateAge(temp);
        //装入什么年份转大运
        List<Integer> tem=new ArrayList<>();
        for(int i=0;i<10;i++){
            tem.add(calendar.get(Calendar.YEAR)+(i*10));
        }
        yearFateInfo.setBigFateYear(tem);
        return yearFateInfo;
    }

    /**
     * 根据天干返回数组索引
     *
     * @return
     */
    private int indexOfGen(String gen) {
        for (int i = 0; i < Gen.length; i++) {
            if (Gen[i].equals(gen)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据地支返回数组索引
     *
     * @return
     */
    private int indexOfZhi(String zhi) {
        for (int i = 0; i < Zhi.length; i++) {
            if (Zhi[i].equals(zhi)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据月柱取大运.
     *
     * @param genIndex 月柱天干
     * @param zhiIndex 月柱地支
     * @param fORb     0：顺行 或者 1：逆行
     * @return
     */
    private List<List> getBigFate(int genIndex, int zhiIndex, int fORb) {
        List<String> bigFateGen = new ArrayList<>();
        List<String> bigFateZhi = new ArrayList<>();
        List<List> result = new ArrayList<>();
        //取十个大运。
        for (int i = 1; i < 9; i++) {
            if (fORb == 0) {
                //顺行。
                bigFateGen.add(this.Gen[(10 + genIndex + i) % 10]);
                bigFateZhi.add(this.Zhi[(12 + zhiIndex + i) % 12]);
            } else {
                //逆行。
                bigFateGen.add(this.Gen[(10 + genIndex - i) % 10]);
                bigFateZhi.add(this.Zhi[(12 + zhiIndex - i) % 12]);
            }

        }
        result.add(bigFateGen);
        result.add(bigFateZhi);
        return result;
    }


    private List<Integer> yearOfFate(PersonInfo personInfo, int fORb) {

        //取出生的年份
        int year = personInfo.getYear();
        //装入calendar对象，用来换算成毫秒。
        Calendar calendar = Calendar.getInstance();
        calendar.set(personInfo.getYear(), personInfo.getMonth() - 1, personInfo.getDay(), personInfo.getHour(), personInfo.getMinute(), personInfo.getSecond());
        //月支
        Long myTime = (calendar.getTimeInMillis() / 1000) * 1000;//日主的出生年月转换成毫秒，除一千是为了清除函数返回后面的三个随机数。
        //用来接收上运岁 月 日 的结果。
        List<Integer> startFate = null;
        List<Map<String, String>> list = new ArrayList<>(); //接收日主所在年份的所有节
        Map<String, String> map = new HashMap<>();//接收当前节气
        // Map<String, String> lastMap = new HashMap<>();//接收最后一个节气，取当前交接节跨年时用。
        //循环直接到 未交接的节气。
        while (startFate == null) {


            if (fORb == 0) {
                list = allJei(year);//接收日主所在年份的所有节
                //顺行。
                //遍历所有节
                for (int i = 0; i < list.size(); i++) {
                    map = list.get(i);//接收当前节
                    Long termTime = TimeToStamp(map.get("date"));//把当前节的具体时间转换成毫秒
                    if (termTime >= myTime) {//把当前节气的毫秒 大于或者等于 日主出生年月，说明没交当前节。
                        map = list.get(i);//返回这个未交的节
                        //下一个节的时间减去出生的时间。
                        startFate = timeOfFate(termTime - myTime);
                        break;
                    }

                }
            } else {
                list = allJei(year + 1);//接收日主所在年份的所有节，因为是逆行，所以要加1，把所有没交的节都取出来循环。
                //逆行。
                //遍历所有节
                for (int i = list.size() - 1; i >= 0; i--) {
                    map = list.get(i);//接收当前节
                    Long termTime = TimeToStamp(map.get("date"));//把当前节的具体时间转换成毫秒
                    if (termTime <= myTime) {//把当前节气的毫秒 大于或者等于 日主出生年月，说明没交当前节。
                        map = list.get(i);//返回这个未交的节

                        Long temp = myTime - termTime;//出生的时间减去上一个节的时间。
                        System.out.println(myTime+temp);
                        startFate = timeOfFate(myTime - termTime);
                        break;
                    }

                }
            }
            //lastMap = map;
            year = fORb == 0 ? year + 1 : year - 1;//所在年的节气都过了，加1/减1 在下一年(上一年)找没交接的节气。
        }
        return startFate;
    }

    /**
     * 返回指定年份的所有节气名字和具体时间
     *
     * @param year
     * @return {date= 1988-03-20 17:38:45, term=春分}
     */
    private List<Map<String, String>> allTerm(Integer year) {
        LunarTerm lunarTerm = new LunarTerm();
        return lunarTerm.JQList(year - 1);
    }

    /**
     * 返回指定年份的所有(节)名字和具体时间
     *
     * @param year
     * @return {date= 1988-03-20 17:38:45, term=春分}
     */
    private List<Map<String, String>> allJei(Integer year) {
        List<Map<String, String>> term = allTerm(year);
        for (int i = 0; i < term.size(); i++) {
            if (i % 2 == 0) {// i % 2 !=0 同样好使
                term.remove(i);
            } else {
                term.remove(i);
            }

        }
        return term;
    }

    /**
     * 把节气取回来的时间转换成long 的时间戳。
     *
     * @param time
     * @return
     */
    private Long TimeToStamp(String time) {
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


    /**
     * 用时间差来计算 上运时间
     *
     * @param timeDiff 日主出生时间和 上/下 一个节的时间差。
     * @return 岁 月 日
     */
    private List<Integer> timeOfFate(Long timeDiff) {
        List<Integer> result = new ArrayList<>();
        Long year = 0L;
        Long month = 0L;
        Long day = 0L;
        Long temp = 0L;
        Long time=0L;
        timeDiff = timeDiff / 1000;
        //三天为一岁
        year = timeDiff / 259200L;
        timeDiff = timeDiff - (year * 259200L);
        time=year*259200L;
        //一日为四个月
        temp = (timeDiff / 86400L);
        month = temp * 4;
        timeDiff = timeDiff - (temp * 86400L);

        //三个时辰（6小时）为一个月。
        temp = timeDiff / 21600L;
        month = month + temp;
        timeDiff = timeDiff - (temp * 21600L);
        //一小时作五天。
        temp = timeDiff / 3600L;
        day = temp * 5;
        timeDiff = timeDiff - (temp * 3600L);
        //12分钟作一日
        temp = timeDiff / 720L;
        day = day + temp;

        //装入结果
        result.add(year.intValue());
        result.add(month.intValue());
        result.add(day.intValue());
        return result;
    }

}
