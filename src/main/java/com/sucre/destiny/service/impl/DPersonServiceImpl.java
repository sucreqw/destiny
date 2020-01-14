package com.sucre.destiny.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.destiny.common.ChineseCalendar;
import com.sucre.destiny.common.PageUtil;
import com.sucre.destiny.common.UserUtil;
import com.sucre.destiny.common.lunarTosolar.Lunar;
import com.sucre.destiny.common.lunarTosolar.LunarSolar;
import com.sucre.destiny.common.lunarTosolar.Solar;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
    public Integer addPerson(Boolean isChinese,Boolean isLeap, PersonInfo personInfo) {
        DPersonDO dPersonDO = new DPersonDO();
        BeanUtils.copyProperties(personInfo,dPersonDO);
        //部分字段需要单独处理.
        dPersonDO.setIsChinese(isChinese?1:0);
        dPersonDO.setIsLeap(isLeap?1:0);
        dPersonDO.setEightWord(JSONArray.toJSONString(personInfo.getEightWord()));
        dPersonDO.setComment(JSONObject.toJSONString(personInfo.getComment()));
        dPersonDO.setCreateId(UserUtil.getCurrentUser());

        baseMapper.insert(dPersonDO);
        return dPersonDO.getId();
    }

    @Override
    public void deletePerson(Integer id) {
        baseMapper.deleteById(id);
    }

    //暂时用不到
    @Override
    public void updatePerson(Boolean isChinese, Boolean isLeap,Integer id, PersonDTO personDTO) {
        /*DPersonDO dPersonDO = baseMapper.selectById(id);
        if (dPersonDO == null) {
            return;
        }
        //BeanUtils.copyProperties(person, dPersonDO);
        dPersonDO.setName(personDTO.getNick());
        dPersonDO.setCreateId(UserUtil.getCurrentUser());
        dPersonDO.setDetail(getTimeInMillis(isChinese,isLeap, personDTO));
        baseMapper.updateById(dPersonDO);*/
    }

    //暂时用不到,先不改了,
    @Override
    public PersonInfo getPerson(Integer id) {
       /* DPersonDO dPersonDO = baseMapper.selectById(id);
        PersonInfo personInfo = new PersonInfo();
        // BeanUtils.copyProperties(dPersonDO,personInfo);
        personInfo.setNick(dPersonDO.getName());
        // personInfo.setComment(dPersonDO.getDetail());
        return personInfo;*/
       return null;
    }

    @Override
    public PersonInfo getPersonByNick(String nick) {
        QueryWrapper<DPersonDO> wrapper = new QueryWrapper<>();
        wrapper.eq("nick",nick);
        DPersonDO dPersonDO = baseMapper.selectOne(wrapper);
        if(dPersonDO!=null){
            PersonInfo personInfo = new PersonInfo();
            BeanUtils.copyProperties(dPersonDO,personInfo);
            //有些字段要单独处理,因为数据库字段不一样.
            personInfo.setEightWord(str2list(dPersonDO.getEightWord()));
            personInfo.setComment((Map<String,Object>)JSONObject.parseObject(dPersonDO.getComment()));
            return personInfo;
        }
       return null;
    }
    //把数据库里的json数据重新转换为list返回到前端
    private List<String> str2list(String source){
       // List<String> ret = new ArrayList<String>();
       // JSONArray.parseArray(source);
        List<String> ret = JSONArray.parseObject(source, new TypeReference<ArrayList<String>>() {});
        return ret;
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

    private String getTimeInMillis(Boolean isChinese, Boolean isLeap,PersonDTO personDTO) {
        Calendar calendar = Calendar.getInstance();
        PersonInfo personInfo = new PersonInfo();
        ChineseCalendar chineseCalendar = null;
        if (isChinese) {
            if (!isLeap) {
                chineseCalendar = new ChineseCalendar(isChinese, personDTO.getYear(), personDTO.getMonth(), personDTO.getDay(), personDTO.getHour(), personDTO.getMinute(), personDTO.getSecond());
                calendar.set(chineseCalendar.get(ChineseCalendar.YEAR), chineseCalendar.get(ChineseCalendar.MONTH), chineseCalendar.get(ChineseCalendar.DATE), chineseCalendar.get(Calendar.HOUR_OF_DAY), chineseCalendar.get(ChineseCalendar.MINUTE), chineseCalendar.get(ChineseCalendar.SECOND));
            } else {
                //农历的闰月，先转换成公历，然后再转换成calendar对象
                LunarSolar lunarSolar = new LunarSolar();
                Lunar lunar = new Lunar();
                lunar.setLunarYear(personDTO.getYear());
                lunar.setLunarMonth(personDTO.getMonth());
                lunar.setLunarDay(personDTO.getDay());
                lunar.setIsleap(true);//闰月
                Solar new_solar = lunarSolar.LunarToSolar(lunar);//转换成公历
                //设置日主的出生年月。calendar的月份从0开始算，所以要减1
                calendar.set(new_solar.getSolarYear(), new_solar.getSolarMonth() - 1, new_solar.getSolarDay(), personDTO.getHour(), personDTO.getMinute(), personDTO.getSecond());
                // chineseCalendar= new ChineseCalendar(calendar);
            }
        } else {
            //设置日主的出生年月。calendar的月份从0开始算，所以要减1
            calendar.set(personDTO.getYear(), personDTO.getMonth() - 1, personDTO.getDay(), personDTO.getHour(), personDTO.getMinute(), personDTO.getSecond());
            // chineseCalendar= new ChineseCalendar(calendar);
        }
        //chineseCalendar = new ChineseCalendar(calendar);
        return String.valueOf(calendar.getTimeInMillis());
    }
}
