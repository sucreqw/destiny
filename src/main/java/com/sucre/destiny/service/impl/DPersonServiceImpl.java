package com.sucre.destiny.service.impl;

import com.sucre.destiny.common.ChineseCalendar;
import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.entity.DPersonDO;
import com.sucre.destiny.dao.DPersonMapper;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.service.IDPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-12
 */
@Service
public class DPersonServiceImpl extends ServiceImpl<DPersonMapper, DPersonDO> implements IDPersonService {

    @Override
    public Integer addPerson(Boolean isChinese, PersonDTO personDTO) {
        DPersonDO dPersonDO=new DPersonDO();
        Calendar calendar = Calendar.getInstance();
        PersonInfo personInfo = new PersonInfo();
        ChineseCalendar chineseCalendar = null;
        if (isChinese) {
            chineseCalendar = new ChineseCalendar(isChinese, personDTO.getYear(), personDTO.getMonth(), personDTO.getDay(), personDTO.getHour(), personDTO.getMinute(), personDTO.getSecond());
            calendar.set(chineseCalendar.get(ChineseCalendar.YEAR), chineseCalendar.get(ChineseCalendar.MONTH), chineseCalendar.get(ChineseCalendar.DATE), chineseCalendar.get(ChineseCalendar.HOUR), chineseCalendar.get(ChineseCalendar.MINUTE), chineseCalendar.get(ChineseCalendar.SECOND));
        } else {
            //设置日主的出生年月。calendar的月份从0开始算，所以要减1
            calendar.set(personDTO.getYear(), personDTO.getMonth() - 1, personDTO.getDay(), personDTO.getHour(), personDTO.getMinute(), personDTO.getSecond());
            chineseCalendar = new ChineseCalendar(calendar);
        }


        baseMapper.insert(dPersonDO);
        return dPersonDO.getId();
    }

    @Override
    public void deletePerson(Integer id) {

    }

    @Override
    public void updatePerson(Integer id, PersonDTO person) {

    }

    @Override
    public PersonInfo getPerson(Integer id) {
        DPersonDO dPersonDO = baseMapper.selectById(id);
        PersonInfo personInfo = new PersonInfo();
        // BeanUtils.copyProperties(dPersonDO,personInfo);
        personInfo.setNick(dPersonDO.getName());
        // personInfo.setComment(dPersonDO.getDetail());
        return personInfo;
    }
}
