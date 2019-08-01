package com.sucre.destiny.controller;

import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.dto.TenGodDTO;
import com.sucre.destiny.info.CommonResult;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.info.TenGodInfo;
import com.sucre.destiny.info.YearFateInfo;
import com.sucre.destiny.service.IEightWord;
import com.sucre.destiny.service.ITenGodService;
import com.sucre.destiny.service.IYearFateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: DestinyControler
 * @Package controler
 * @Description: 返回十个大运，小运，流年
 * @date 2019-05-08 14:12
 */
@RestController
@RequestMapping("/YearFate")
public class YearFateController {
    @Autowired
    IYearFateService iYearFateService;
    @Autowired
    IEightWord iEightWord;


    @PostMapping("/")
    public CommonResult<YearFateInfo> getTenGod(@RequestParam(defaultValue ="false") Boolean isLeap, @RequestParam(defaultValue ="false") Boolean isChinese , @RequestBody PersonDTO person) {
        CommonResult<YearFateInfo> result = new CommonResult<>();
        PersonInfo personInfo=iEightWord.time2Person(isLeap,isChinese,person);
        YearFateInfo yearFateInfo=iYearFateService.bigFate(personInfo);
        result.setData(yearFateInfo);
        return result;
    }
}
