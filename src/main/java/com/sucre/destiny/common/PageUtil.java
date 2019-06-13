package com.sucre.destiny.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PageUtil {

    public static  <T> Page<T> buildPage(IPage page, Class<T> clazz){
        Page<T> pageResult = new Page<>();
        BeanUtils.copyProperties(page,pageResult);
        pageResult.setRecords((List<T>) page.getRecords().stream().map(item -> {
            T t ;
            try {
                t= clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            BeanUtils.copyProperties(item,t);
            return t;
        }).collect(Collectors.toList()));
        return pageResult;
    }

}
