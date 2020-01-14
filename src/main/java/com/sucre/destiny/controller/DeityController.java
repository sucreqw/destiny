package com.sucre.destiny.controller;

import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.info.CommonResult;
import com.sucre.destiny.info.DeityInfo;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.service.DeityService;
import com.sucre.destiny.service.IDPersonService;
import com.sucre.destiny.service.IEightWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: DeityControler
 * @Package controler
 * @Description: 其它神煞类的判断, 如阴差阳错, 十恶大败等, 以后的要加神煞也在这里, 比如天德, 乙德等.....
 * @date 2023-1-14 14:12
 */
@RestController
@RequestMapping("/Deity")
public class DeityController {

    @Autowired
    IDPersonService idPersonService;
    @Autowired
    IEightWord iEightWord;
    @Autowired
    DeityService deityService;

    @PostMapping("/")
    public CommonResult<List<DeityInfo>> getDeity(@RequestParam(defaultValue = "false") Boolean isLeap, @RequestParam(defaultValue = "false") Boolean isChinese, @RequestBody PersonDTO person) {
        PersonInfo personInfo=new PersonInfo();
        CommonResult<List<DeityInfo>> result = new CommonResult<>();

        if(!person.getNick().equals("")){
            personInfo=idPersonService.getPersonByNick(person.getNick());
        }
        if(personInfo==null) {
            //没有找到之前有查过的记录
            //按第一次查询处理
            personInfo = iEightWord.time2Person(isLeap, isChinese, person);
        }
        List<DeityInfo> deityInfo=deityService.allDeity(personInfo);
        result.setData(deityInfo);
        return result;
    }
}
