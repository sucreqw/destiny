package com.sucre.destiny.controller;

import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.info.CommonResult;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.info.WeightInfo;
import com.sucre.destiny.service.IEightWord;
import com.sucre.destiny.service.IWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Weight")
public class WeightController {
    @Autowired
    IEightWord iEightWord;
    @Autowired
    IWeightService iWeightService;
    /**
     * 根据出生年月日返回称重的八字
     *
     * @param isChinese 是否是农历
     * @return
     */

    @PostMapping("/")
    public CommonResult<WeightInfo> getEightWord(@RequestParam(defaultValue ="false") Boolean isLeap ,@RequestParam(defaultValue ="false") Boolean isChinese , @RequestBody PersonDTO person) {
        CommonResult<WeightInfo> result = new CommonResult<>();
        PersonInfo personInfo=iEightWord.time2Person(isLeap,isChinese,person);
        result.setData(iWeightService.WeightMe(personInfo));
        return result;
    }
}
