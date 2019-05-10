package com.sucre.destiny.controller;

import com.sucre.destiny.entity.Person;
import com.sucre.destiny.service.ConvertToEightWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: DestinyControler
 * @Package controler
 * @Description: 返回前端的各种接口。
 * @date 2019-05-08 14:12
 */
@RestController
public class DestinyController {

    //自动注入service层，年月日转换为对应的对象。
    @Autowired
    ConvertToEightWord convertToEightWord;


    /**
     * 根据出生年月日返回具体的八字
     *
     * @param isChinese 是否是农历
     * @param year      出生年份
     * @param month     出生月份
     * @param day       出生日
     * @param nick      昵称
     * @return
     */
      @RequestMapping(method = RequestMethod.POST, value = "/getEightWord")
    public Person getEightWord(boolean isChinese, int year, int month, int day, int hour,int min,int sec,String nick) {
        return convertToEightWord.timeToEight(isChinese, year, month, day, hour,min,sec,nick);
    }
}
