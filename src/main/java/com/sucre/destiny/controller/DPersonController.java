package com.sucre.destiny.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.info.CommonResult;
import com.sucre.destiny.info.DPersonInfo;
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
    public CommonResult<Integer> addPerson(@RequestParam(defaultValue ="false") Boolean isChinese, @RequestBody PersonDTO person) {
        CommonResult<Integer> result = new CommonResult<>();
        //isChinese=isChinese==null?false:isChinese;
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
    @PutMapping("/{id}")
    public void updataPerson(@RequestParam(defaultValue ="false") Boolean isChinese,@PathVariable Integer id, @RequestBody PersonDTO personDTO) {
        idPersonService.updatePerson( isChinese,id, personDTO);
    }
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        idPersonService.deletePerson(id);
    }

    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<DPersonInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String name) {
        CommonResult<Page<DPersonInfo>> result = new CommonResult<>();
        Page<DPersonInfo> list = idPersonService.listPerson(page, pageSize,name);
        result.setData(list);
        return result;
    }
}

