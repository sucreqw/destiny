package com.sucre.destiny.controller;

import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.info.CommonResult;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.service.IEightWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: DestinyControler
 * @Package controler
 * @Description: 返回前端的各种接口。
 * @date 2019-05-08 14:12
 */
@RestController
@RequestMapping("/Destiny")
public class DestinyController {

    //自动注入service层，年月日转换为对应的对象。
    @Autowired
    IEightWord iEightWord;

    PersonInfo personInfo;

    /**
     * 根据出生年月日返回具体的八字
     *
     * @param isChinese 是否是农历
     * @return
     */

    @PostMapping("/")
    public CommonResult<PersonInfo> getEightWord(@RequestParam(defaultValue ="false") Boolean isChinese , @RequestBody PersonDTO person) {
        CommonResult<PersonInfo> result = new CommonResult<>();
        //isChinese=isChinese==null?false:isChinese;
        result.setData(iEightWord.time2Person(isChinese,person));
        return result;
    }
    
}
