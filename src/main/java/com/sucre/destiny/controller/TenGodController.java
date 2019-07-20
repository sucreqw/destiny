package com.sucre.destiny.controller;

import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.dto.TenGodDTO;
import com.sucre.destiny.info.CommonResult;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.info.TenGodInfo;
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

    /**
     *  传入八字对象，返回对应的十神和藏干。
     * @param eightWord
     * @return
     */
    @PostMapping("/")
    public CommonResult<TenGodInfo> getEightWord(@RequestBody TenGodDTO eightWord) {
        CommonResult<TenGodInfo> result = new CommonResult<>();
        HashMap<String,String> h=new HashMap<>();
        h.put("test","v");
        h.put("22","55");
        TenGodInfo tenGodInfo=new TenGodInfo();
        tenGodInfo.setTest(h);
        return result;
    }
}
