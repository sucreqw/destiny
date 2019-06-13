package com.sucre.destiny.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: TermToEarth
 * @Package service
 * @Description: 将节气转换为对应的地支。
 * @date 2019-05-09 10:43
 */
@Service
public class TermToEarth {
    //用来存放节气对应月份的map
    Map<String,String> termAndMonth=new HashMap<>();
    public TermToEarth() {
        //加载对应的节气和月份。
        termAndMonth.put("立春","寅");
        termAndMonth.put("雨水","寅");
        termAndMonth.put("惊蛰","卯");
        termAndMonth.put("春分","卯");
        termAndMonth.put("清明","辰");
        termAndMonth.put("谷雨","辰");
        termAndMonth.put("立夏","巳");
        termAndMonth.put("小满","巳");
        termAndMonth.put("芒种","午");
        termAndMonth.put("夏至","午");
        termAndMonth.put("小暑","未");
        termAndMonth.put("大暑","未");
        termAndMonth.put("立秋","申");
        termAndMonth.put("处暑","申");
        termAndMonth.put("白露","酉");
        termAndMonth.put("秋分","酉");
        termAndMonth.put("寒露","戌");
        termAndMonth.put("霜降","戌");
        termAndMonth.put("立冬","亥");
        termAndMonth.put("小雪","亥");
        termAndMonth.put("大雪","子");
        termAndMonth.put("冬至","子");
        termAndMonth.put("小寒","丑");
        termAndMonth.put("大寒","丑");

        //地支对应的月份
        termAndMonth.put("子","11");
        termAndMonth.put("丑","12");
        termAndMonth.put("寅","1");
        termAndMonth.put("卯","2");
        termAndMonth.put("辰","3");
        termAndMonth.put("巳","4");
        termAndMonth.put("午","5");
        termAndMonth.put("未","6");
        termAndMonth.put("申","7");
        termAndMonth.put("酉","8");
        termAndMonth.put("戌","9");
        termAndMonth.put("亥","10");
    }

    /**
     * 根据节气返回对应的地支月份
     * @param term 节气名字。
     * @return
     */
    public String termToMonthEarth(String term){
        return termAndMonth.get(term);
    }
}
