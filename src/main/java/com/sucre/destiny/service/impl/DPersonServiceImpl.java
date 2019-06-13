package com.sucre.destiny.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.destiny.common.ChineseCalendar;
import com.sucre.destiny.common.PageUtil;
import com.sucre.destiny.common.UserUtil;
import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.entity.DPersonDO;
import com.sucre.destiny.dao.DPersonMapper;
import com.sucre.destiny.info.DPersonInfo;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.service.IDPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

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

        dPersonDO.setName(personDTO.getNick());
        dPersonDO.setCreateId(UserUtil.getCurrentUser());
        dPersonDO.setDetail(getTimeInMillis(isChinese,personDTO));

        baseMapper.insert(dPersonDO);
        return dPersonDO.getId();
    }

    @Override
    public void deletePerson(Integer id) {
       baseMapper.deleteById(id);
    }

    @Override
    public void updatePerson(Boolean isChinese,Integer id, PersonDTO personDTO) {
        DPersonDO dPersonDO = baseMapper.selectById(id);
        if (dPersonDO == null) {
            return;
        }
        //BeanUtils.copyProperties(person, dPersonDO);
        dPersonDO.setName(personDTO.getNick());
        dPersonDO.setCreateId(UserUtil.getCurrentUser());
        dPersonDO.setDetail(getTimeInMillis(isChinese,personDTO));
        baseMapper.updateById(dPersonDO);
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

    @Override
    public Page<DPersonInfo> listPerson(Integer page, Integer pageSize, String name) {
        QueryWrapper<DPersonDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        Page<DPersonDO> DPersonInfoPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(DPersonInfoPage, wrapper), DPersonInfo.class);
    }

    private String getTimeInMillis(Boolean isChinese,PersonDTO personDTO){
        Calendar calendar = Calendar.getInstance();
        ChineseCalendar chineseCalendar = null;
        if (isChinese) {
            chineseCalendar = new ChineseCalendar(isChinese, personDTO.getYear(), personDTO.getMonth(), personDTO.getDay(), personDTO.getHour(), personDTO.getMinute(), personDTO.getSecond());
            //System.out.println(chineseCalendar.get(Calendar.HOUR));
            calendar.set(chineseCalendar.get(ChineseCalendar.YEAR), chineseCalendar.get(ChineseCalendar.MONTH), chineseCalendar.get(ChineseCalendar.DATE), chineseCalendar.get(ChineseCalendar.HOUR), chineseCalendar.get(ChineseCalendar.MINUTE), chineseCalendar.get(ChineseCalendar.SECOND));
        } else {
            //设置日主的出生年月。calendar的月份从0开始算，所以要减1
            calendar.set(personDTO.getYear(), personDTO.getMonth() - 1, personDTO.getDay(), personDTO.getHour(), personDTO.getMinute(), personDTO.getSecond());
        }
        return String.valueOf(calendar.getTimeInMillis());
    }
}
