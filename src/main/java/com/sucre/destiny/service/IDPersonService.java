package com.sucre.destiny.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.entity.DPersonDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.destiny.info.DPersonInfo;
import com.sucre.destiny.info.PersonInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-12
 */
public interface IDPersonService extends IService<DPersonDO> {
    Integer addPerson(Boolean isChinese,PersonDTO person);
    void deletePerson(Integer id);
    void updatePerson(Boolean isChinese,Integer id,PersonDTO person);
    PersonInfo getPerson(Integer id);
    Page<DPersonInfo> listPerson(Integer page, Integer pageSize, String name);
}
