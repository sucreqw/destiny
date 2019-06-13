package com.sucre.destiny.service;

import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.entity.DPersonDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.destiny.info.PersonInfo;

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
    void updatePerson(Integer id,PersonDTO person);
    PersonInfo getPerson(Integer id);
}
