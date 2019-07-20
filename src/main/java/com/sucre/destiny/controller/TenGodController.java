package com.sucre.destiny.controller;

import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.dto.TenGodDTO;
import com.sucre.destiny.info.CommonResult;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.info.TenGodInfo;
import com.sucre.destiny.service.ITenGodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: DestinyControler
 * @Package controler
 * @Description: 返回八字对应的十神，藏干以及藏干对应的十神。
 * @date 2019-05-08 14:12
 */
@RestController
@RequestMapping("/TenGod")
public class TenGodController {
    @Autowired
    ITenGodService iTenGodService;
    /**
     *  传入八字对象，返回对应的十神和藏干。
     * @param eightWord
     * @return
     */
    @PostMapping("/")
    public CommonResult<TenGodInfo> getTenGod(@RequestBody TenGodDTO eightWord) {
        CommonResult<TenGodInfo> result = new CommonResult<>();
         TenGodInfo tenGodInfo=iTenGodService.eightToGod(eightWord);
         result.setData(tenGodInfo);
        return result;
    }
}
