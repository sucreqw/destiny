package com.sucre.destiny.service.impl;

import com.sucre.destiny.info.DeityInfo;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.service.DeityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sucre chen 906509023@qq.com
 * @Title: DeityImpl
 * @Package Impl
 * @Description: 其它神煞类的判断, 如阴差阳错, 十恶大败等, 以后的要加神煞也在这里, 比如天德, 乙德等.....
 * @date 2023-1-14 14:12
 */
@Service
public class DeityImpl implements DeityService {
    /**
     * 总入口,不做具体操作,只做分配调用功能.
     * @param personInfo
     * @return
     */
    @Override
    public List<DeityInfo> allDeity(PersonInfo personInfo) {
        //把每一个计算出来的结果都放到结果集,统一返回到前端.
        //要求每个结果空都必须返回null,避免返回空结果集到前端.
        List<DeityInfo> result=new ArrayList<>();
        DeityInfo deityInfo=new DeityInfo();
        deityInfo=allFalse(personInfo);
        if(deityInfo!=null){result.add(deityInfo);}

    return result;
    }

    /**
     * 十恶大败日
     * @param personInfo
     * @return
     */
    private DeityInfo tenBad(PersonInfo personInfo){
        List<String> eight=personInfo.getEightWord();
        DeityInfo deityInfo=new DeityInfo();
        return null;
    }
    /**
     * 命犯陰差陽錯
     * @param personInfo
     * @return
     */
    private DeityInfo allFalse(PersonInfo personInfo){
        List<String> eight=personInfo.getEightWord();
        DeityInfo deityInfo=new DeityInfo();
        deityInfo.setTitle("陰差陽錯");
        deityInfo.setDescription("阴阳交错是如何 辛卯壬辰癸巳多\n" +
                "丙午丁未戊申位 辛酉壬戌癸亥过\n" +
                "丙子丁丑戊寅日 十二宫中仔细歌\n" +
                "好风流处不风流 花烛迎郎不自由\n" +
                "不是填房因孝娶 洞房定结两家仇\n" +
                "不论男女月日时两三重犯之极重.");
        deityInfo.setSolutions("认外家或认神即可.");

        if(eight.get(4).equals("辛") && eight.get(5).equals("卯")){return deityInfo;}
        if(eight.get(4).equals("壬") && eight.get(5).equals("辰")){return deityInfo;}
        if(eight.get(4).equals("癸") && eight.get(5).equals("巳")){return deityInfo;}
        if(eight.get(4).equals("丙") && eight.get(5).equals("午")){return deityInfo;}
        if(eight.get(4).equals("丁") && eight.get(5).equals("未")){return deityInfo;}
        if(eight.get(4).equals("戊") && eight.get(5).equals("申")){return deityInfo;}
        if(eight.get(4).equals("辛") && eight.get(5).equals("酉")){return deityInfo;}
        if(eight.get(4).equals("壬") && eight.get(5).equals("戌")){return deityInfo;}
        if(eight.get(4).equals("癸") && eight.get(5).equals("亥")){return deityInfo;}
        if(eight.get(4).equals("丙") && eight.get(5).equals("子")){return deityInfo;}
        if(eight.get(4).equals("丁") && eight.get(5).equals("丑")){return deityInfo;}
        if(eight.get(4).equals("戊") && eight.get(5).equals("寅")){return deityInfo;}
        return null;
    }

}
