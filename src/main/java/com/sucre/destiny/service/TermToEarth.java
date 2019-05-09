package com.sucre.destiny.service;

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
        termAndMonth.put("惊蛰","卯");
        termAndMonth.put("清明","辰");
        termAndMonth.put("立夏","巳");
        termAndMonth.put("芒种","午");
        termAndMonth.put("小暑","未");
        termAndMonth.put("立秋","申");
        termAndMonth.put("白露","酉");
        termAndMonth.put("寒露","戌");
        termAndMonth.put("立冬","亥");
        termAndMonth.put("大雪","子");
        termAndMonth.put("小寒","丑");
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
