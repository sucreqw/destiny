package com.sucre.destiny.service.impl;

import com.sucre.destiny.dto.TenGodDTO;
import com.sucre.destiny.info.TenGodInfo;
import com.sucre.destiny.service.ITenGodService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TenGodImpl implements ITenGodService {
    private final String[] positiveGod = {"比", "食", "才", "杀", "枭"};
    private final String[] negativeGod = {"劫", "伤", "财", "官", "印"};
    private final String[] Five = {"木", "火", "土", "金", "水"};
    private final String[] Gen = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private final String[] ZhiFive = {"木", "火", "金", "水", "土", "土"};
    private final String[] Zhi = {"寅", "卯", "巳", "午", "申", "酉", "亥", "子", "丑", "辰", "未", "戌",};
    private final Object[] HideGen = {

            //寅藏 甲 丙 戊
            new Integer[]{0, 2, 4},
            //卯藏 乙
            new Integer[]{1},

            //巳藏 丙 庚 戊
            new Integer[]{2, 6, 4},
            //午藏 丁 己
            new Integer[]{3, 5},

            //申藏 庚 壬 戊
            new Integer[]{6, 8, 4},
            //酉藏 辛
            new Integer[]{7},

            //亥藏 壬 甲
            new Integer[]{8, 0},
            //子藏 癸
            new Integer[]{9},
            //丑藏 癸 辛 己
            new Integer[]{5, 9, 7},
            //辰藏 戊 乙 癸
            new Integer[]{4, 1, 9},
            //未藏 己 丁 乙
            new Integer[]{5, 3, 1},
            //戌藏 戊 辛 丁
            new Integer[]{4, 7, 3},
    };


    @Override
    public TenGodInfo eightToGod(TenGodDTO tenGodDTO) {
        TenGodInfo tenGodInfo = new TenGodInfo();
        //用来装八字对应的五行
        String[] allFive = new String[8];
        //取出日主
        String person = tenGodDTO.getDayGen();
        //计算日主阴阳和五行属性.
        int five = indexOfGen(person);
        allFive[2] = Five[five / 2];
        //把年干，月干，时干，放到list里，然后循环取对应的十神
        ArrayList<String> eight = new ArrayList<>();
        eight.add(tenGodDTO.getYearGen());
        eight.add(tenGodDTO.getMonthGen());
        eight.add(tenGodDTO.getHourGen());
        //用来记录装进去的五行
        int k = 0;
        //用来记录要装数据到info类的索引。
        int p = 0;
        //用来记录天干对应的十神
        String[] result = new String[8];
        for (String temp : eight) {
            //取干的对应阴阳
            int genFive = indexOfGen(temp);

            //跳过日主的位置。
            if (p == 2) {
                p++;
                k++;
            }
            //取对应的五行。
            allFive[k] = Five[genFive / 2];

            /*//阴阳属性和日主的相同
            if ((five % 2) == (genFive % 2)) {
                //同我者 比食才杀枭
                //装入计算得到的对应十神
                //result[p] = positiveGod[(5 + (genFive / 2) - (five / 2)) % 5];
                tenGodInfo.setByIndex(p, positiveGod[(5 + (genFive / 2) - (five / 2)) % 5]);

            } else {
                //异我者 劫伤财官印
                //阴阳属性和日主的不同
                //装入计算得到的对应十神
                //result[p] = negativeGod[(5 + (genFive / 2) - (five / 2)) % 5];
                tenGodInfo.setByIndex(p, negativeGod[(5 + (genFive / 2) - (five / 2)) % 5]);
            }*/


            //装入计算得到的对应十神
            tenGodInfo.setByIndex(p, GenToGod(five, genFive));
            p++;
            k++;
        }


        //地支藏干
        eight = new ArrayList<>();
        eight.add(tenGodDTO.getYearZhi());
        eight.add(tenGodDTO.getMonthZhi());
        eight.add(tenGodDTO.getDayZhi());
        eight.add(tenGodDTO.getHourZhi());

        for (String temp : eight) {
            //取支的对应索引
            int zhiFive = indexOfZhi(temp);
            //取地支对应的五行。
            allFive[k] = ZhiFive[zhiFive / 2];
            //根据地支索引取出所有藏干的索引，然后再把对应的藏干装进hideGens数组里。
            Integer[] hideGenIndex = (Integer[]) HideGen[zhiFive];
            //记录本地支的所有藏干
            String[] hideGens = new String[hideGenIndex.length];
            //记录本地支的藏干对应的十神
            String[] hideGensGod = new String[hideGenIndex.length];
            for (int j = 0; j < hideGenIndex.length; j++) {
                //装入对应的天干
                hideGens[j] = Gen[hideGenIndex[j]];
                //把取到的天干，再取五行索引，然后用genToGod转换对应的十神，装入到数组。
                hideGensGod[j] = GenToGod(five, indexOfGen(hideGens[j]));
            }

            //装入对应地支的藏干
            tenGodInfo.setByIndex(p, hideGens);

            p++;
            //装入对应地支的藏干的十神
            tenGodInfo.setByIndex(p, hideGensGod);
            p++;
            k++;
        }
        tenGodInfo.setByIndex(p, allFive);
        // System.out.println(Arrays.toString(result));
        // System.out.println(Arrays.toString(allFive));
        return tenGodInfo;
    }

    /**
     * 根据传入的日主 天干,把列表里的天干 全部转换对应的十神.
     *
     * @param gen 日主
     * @param dayGen 要转换的天干.例如大运.
     * @return
     */
    @Override
    public List<String> listToGod(List<String> gen, String dayGen) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < gen.size(); i++) {
            //循环把日主天干的索引,列表天干的索引 传入GenToGod,得到对应的十神,加到返回列表
             result.add(GenToGod(indexOfGen(dayGen),indexOfGen(gen.get(i))));
        }
        return result;
    }

    /**
     * 根据天干返回数组索引
     *
     * @return
     */
    private int indexOfGen(String gen) {
        for (int i = 0; i < Gen.length; i++) {
            if (Gen[i].equals(gen)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据地支返回数组索引
     *
     * @return
     */
    private int indexOfZhi(String zhi) {
        for (int i = 0; i < Zhi.length; i++) {
            if (Zhi[i].equals(zhi)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据天干返回对应的十神
     *
     * @param five    日主所属天干的属性索引
     * @param genFive 要获取的天干的属性索引
     * @return
     */
    private String GenToGod(int five, int genFive) {

        //阴阳属性和日主的相同
        if ((five % 2) == (genFive % 2)) {
            //同我者 比食才杀枭
            //装入计算得到的对应十神
            return positiveGod[(5 + (genFive / 2) - (five / 2)) % 5];
            // tenGodInfo.setByIndex(p, positiveGod[(5 + (genFive / 2) - (five / 2)) % 5]);

        } else {
            //异我者 劫伤财官印
            //阴阳属性和日主的不同
            //装入计算得到的对应十神
            return negativeGod[(5 + (genFive / 2) - (five / 2)) % 5];
            //tenGodInfo.setByIndex(p, negativeGod[(5 + (genFive / 2) - (five / 2)) % 5]);
        }
    }
}
