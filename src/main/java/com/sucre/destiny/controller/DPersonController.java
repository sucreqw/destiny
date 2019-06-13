package com.sucre.destiny.controller;


import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.info.CommonResult;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.service.IDPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-06-12
 */
@RestController
@RequestMapping("/dPersonDO")
public class DPersonController {

    //自动注入service层，年月日转换为对应的对象。
    @Autowired
    IDPersonService idPersonService;

    PersonInfo personInfo;
    /**
     * 保存一个命例的出生年月日时。
     * @param isChinese 是否是农历
     * @return
     */
    @PostMapping("/")
    public CommonResult<Integer> addPerson(boolean isChinese, @RequestBody PersonDTO person) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = idPersonService.addPerson(isChinese,person);
        result.setData(id);
        return result;
    }

    @GetMapping("/{id}")
    public CommonResult<PersonInfo> getPerson(Integer id) {
        CommonResult<PersonInfo> result = new CommonResult<>();
        result.setData(idPersonService.getPerson(id));
        return result;
    }
}

