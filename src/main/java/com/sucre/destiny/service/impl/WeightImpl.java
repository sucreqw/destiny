package com.sucre.destiny.service.impl;

import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.info.WeightInfo;
import com.sucre.destiny.service.IWeightService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeightImpl implements IWeightService {
    public final String[] JIA_ZI = {
            "甲子", "乙丑", "丙寅", "丁卯", "戊辰", "己巳", "庚午", "辛未", "壬申", "癸酉",
            "甲戌", "乙亥", "丙子", "丁丑", "戊寅", "己卯", "庚辰", "辛巳", "壬午", "癸未",
            "甲申", "乙酉", "丙戌", "丁亥", "戊子", "己丑", "庚寅", "辛卯", "壬辰", "癸巳",
            "甲午", "乙未", "丙申", "丁酉", "戊戌", "己亥", "庚子", "辛丑", "壬寅", "癸卯",
            "甲辰", "乙巳", "丙午", "丁未", "戊申", "己酉", "庚戌", "辛亥", "壬子", "癸丑",
            "甲寅", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥"
    };
    public final Double[] YEAR_WEIGHT = {
            1.2, 0.9, 0.6, 0.7, 1.2, 0.5, 0.9, 0.8, 0.7, 0.8,
            1.5, 0.9, 1.6, 0.8, 0.8, 1.9, 1.2, 0.6, 0.8, 0.7,
            0.5, 1.5, 0.6, 1.6, 1.5, 0.7, 0.9, 1.2, 1.0, 0.7,
            1.5, 0.6, 0.5, 1.4, 1.4, 0.9, 0.7, 0.7, 0.9, 1.2,
            0.8, 0.7, 1.3, 0.5, 1.4, 0.5, 0.9, 1.7, 0.5, 0.7,
            1.2, 1.9, 0.8, 0.6, 1.9, 0.6, 0.8, 1.6, 1.0, 0.7
    };


    public final String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    /*正月 六钱 二月 七钱 三月 一两八钱 四月 九钱
    五月 五钱 六月 一两六钱 七月 九钱 八月 一两五钱
    九月 一两八钱 十月 八钱 冬月 九钱 腊月 五钱*/
    public final Double[] Mouth_WEIGHT = {
            0.9, 0.5, 0.6, 0.7, 1.8, 0.9, 0.5, 1.6, 0.9, 1.5, 1.8, 0.8
    };
    /*子时 一两六钱 丑时 六钱 寅时 七钱 卯时 一两 辰时 九钱 巳时 一两六钱
   午时 一两 未时八钱 申时 八钱 酉时 九钱 戌时 六钱 亥时 六钱
   */
    public final Double[] HOUR_WEIGHT = {
            1.6, 0.6, 0.7, 1.0, 0.9, 1.6, 1.0, 0.8, 0.8, 0.9, 0.6, 0.6
    };
    /*初一 五钱 初二 一两 初三 八钱 初四 一两五钱 初五 一两六钱
      初六 一两五钱 初七 八钱 初八 一两六钱 初九 八钱 初十 一两六钱
      十一 九钱 十二 一两七钱 十三 八钱 十四 一两七钱 十五 一两
      十六 八钱 十七 九钱 十八 一两八钱 十九 五钱 二十 一两五钱
      廿一 一两 廿二 九钱 廿三 八钱 廿四 九钱 廿五 一两五钱
      廿六 一两八钱 廿七 七钱 廿八 八钱 廿九 一两六钱 三十 六钱*/
    public final String[] DAY_CH = {
            "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
            "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
            "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"
    };
    public final Double[] DAY_WEIGHT = {
            0.5, 1.0, 0.8, 1.5, 1.6, 1.5, 0.8, 1.6, 0.8, 1.6,
            0.9, 1.7, 0.8, 1.7, 1.0, 0.8, 0.9, 1.8, 0.5, 1.5,
            1.0, 0.9, 0.8, 0.9, 1.5, 1.8, 0.7, 0.8, 1.6, 0.6
    };
    public final Object[] resultWeight = {
            new String[]{"二两一钱","短命非业谓大空，平生灾难事重重，\n" +"凶祸频临陷逆境，终世困苦事不成。\n","身寒骨冷苦伶仃，此命推来行乞人，碌碌苦苦无乐日，终生孤单过一生。"},
            new String[]{"二两二钱", "身寒骨冷苦伶仃，此命推来行乞人，\n" + "劳劳碌碌无度日，终年打拱过平生。\n","此命劳碌一生穷，每逢困难事重重，凶祸频临陷苦境，终身大事谋不成。"},
            new String[]{"二两三钱", "此命推来骨肉轻，求谋做事事难成，\n" +"妻儿兄弟实难靠，外出他乡做散人。\n","此命推来骨肉轻，求谋做事事难成，妻儿兄弟实难靠，外出他乡做善人,终身困苦之命。"},
            new String[]{"二两四钱", "此命推来福禄无，门庭困苦总难荣，\n"+  "六亲骨肉皆无靠，流浪他乡作老翁。\n","一生薄福之命。"},
            new String[]{"二两五钱", "此命推来祖业微，门庭营度似稀奇，\n" +  "六亲骨肉如冰炭，一世勤劳自把持。\n","六亲无靠，自力更生之命。"},
            new String[]{"二两六钱","平生衣禄苦中求，独自营谋事不休，\n" + "离祖出门宜早计，晚来衣禄自无休。\n","此命为人刚强，劳心劳力，移祖居住，有能自力得安然，知轻识重，坏事不做，老来贪心口无毒，但一 生不足，子息难靠。初限之中小发达，早年家计得安康，四十七八岁，交来末运渐渐谋事而成，事业而就，财源 茂盛，老来荣华。妻宫有克，两妻无刑，子息四个只一子送终，寿元七十九，过此七十九岁，死于十二月中。"},
            new String[]{"二两七钱", "一生作事少商量，难靠祖宗作主张，\n" + "独马单枪空做去，早年晚岁总无长。\n","此命为人性纯不刚不柔，心中无毒，做事有始有终，池塘鸳鸯寻食吃，易聚易散，骨肉六亲不得力，财 物风云，操心劳力，极早恨奋寒窗，原来破尽，重新白手起家，且过三十五六，方可成家立业，四十外行船顺 风，五十安稳，末限滔滔事业兴，妻宫硬配，子女送终，寿元七十，死于五月中。"},
            new String[]{"二两八钱", "一生行事似飘蓬，祖宗产业在梦中，\n"  + "若不过房改名姓，也当移徒二三通。\n" ,"此命为人多才能，心机灵巧，祖业飘零，离乡别井可成事业，兄弟多力，驳杂多端，为静处安然，出外 有人敬重，可进四方之财，有贵人扶持，逢凶化吉，勤俭一生，无大难，只是救人无功，恩中招怨，重义轻才，易聚易散，早年不能聚财，三十三岁方知劳苦，凡事顺意，三十而立，四十岁称心如意，末限福如东海，寿比 南山。只是妻宫有克，三子送终，寿元六十九，闯过八十一，死于三月中。"},
            new String[]{"二两九钱", "初年运限未曾亨，纵有功名在后成，\n" + "须过四旬才可立，移居改姓始为良。\n"  ,"此命为人性爆，心直口快，有才能，见善不欺，逢恶不怕，事有始终，量能宽大，但不能聚财，兄弟六亲无力，自立家计，出外方好，初限二十三四五不遂，二十七八有好运到，犹如枯木逢春，中限四十九之命有险，四十多来古镜重磨，明月再圆。五十六七八末限明月又被云侵，交七十方走大运，妻小配怕刑，克子，寿元七十七，死于春光中。"},
            new String[]{"三两", "劳劳碌碌苦中求，东奔西走何日休，\n" + "若使终身勤与俭，老来稍可免忧愁。\n"  ,"此命为人多才多能，心机为巧，祖业凋零，离乡别井可成家业，兄弟少力，驳杂多端，出外有贵人扶持，一生无刑克，无大难，只是救人无功，恩中招怨，重义轻才，易聚易散，早年不能聚财，三十三岁方知劳苦，凡事顺意，三十而立，四十岁称心如意，三子送终，寿元六十九，死于三月中。"},
            new String[]{"三两一钱", "忙忙碌碌苦中求，何日云开见日头，\n"  +  "难得祖基家可立，中年衣食渐能周。\n"  ,"此命推来敬重双亲，有福有禄，六亲和睦，义气高强，少年勤学有功名，忠孝双全，心中无毒，不贵则福，出外受人钦佩，四海闻名，老来荣华，限上无忧，一生安康，年轻欠利，末限安享福禄，白鹤先生云：此命三限，有子孙旺相局，初限早成家计，辛勤劳苦，中限渐渐生财重奔江山，夫妻少配无刑，末限荣华富贵，寿元八十三岁，死于冬月之中。"},
            new String[]{"三两二钱", "初年运蹇事难谋，渐有财源如水流，\n" +  "到得中年衣食旺，那时名利一齐收。\n"  ,"中限交来渐渐称心，求谋顺利，出外有人恭敬，一生受贵，若要问其消息，事业兴，家业旺，其年运到滔滔财源至，滚滚利丰盈，春光花自发，微风细雨生，四十六七八交末运，移花接子桂花香，夫妻偕老，寿元八十之外，子孙福禄荣昌，死于腊月中。"},
            new String[]{"三两三钱", "早年作事事难成，百计徒劳枉费心，\n"+ "半世自如流水去，后来运到得黄金。\n"  ,"此命生人性巧心灵，弄假成真，口快无心，恩中招怨，君子敬佩，小人气恨，骨肉无援，志在四方，身心健康，前运乘阴少种树，中限轻财，大运交来，声名可望，万事更新，名利振建，此后小事宜注意，才有子息，寿元八十三，死于三月中。"},
            new String[]{"三两四钱", "此命福气果如何，僧道门中衣禄多，\n" + "离祖出家方为妙，朝晚拜佛念弥陀。\n"   ,"此命推来为人性躁，与人做事反为不美，离祖成家，三翻四次自成自立安享福，直自三十六至四十六，财不谋而自至，福不求而自得，有贵人助，家庭安宁，妻宫若要无刑，猴、猪、羊、蛇不可配，龙、虎、马、牛方得安，虽有二子，终生带暗方可。兄弟六亲如冰碳，在家不得安然，初限驳杂多端，劳碌奔波不能聚钱，常有忧愁，寿元七十八岁，死于三月中。"},
            new String[]{"三两五钱",   "生平福量不周全，祖业根基觉少传，\n" +  "营事生涯宜守旧，时来衣食胜从前。\n"   ,"此命为人品性纯和，做事忠直，志气高傲，与人做事恩中招怨，六亲兄弟不得力，祖业全无，早年驳杂多端，独马单枪，初限命运甚来，二十而立三十来岁末曾交运都说好，三十五六到四十犹如金秋菊迎秋放，心机用尽方逢春，末限交来始称怀，祖业有破后重兴，犹如枯木逢春再开花，妻宫忧虚无刑，寿元五十七，限至六十九，三子送终，寿元八十一，死于十月中。"},
            new String[]{"三两六钱",   "不须劳碌过平生，独自成家福不轻，\n" + "早有福星常照命，任君行去百般成。\n"   ,"此命为人灵机性巧，胸襟通达，志气高，少年勤学有功名之格，青年欠利，腹中多谋，有礼有义，有才能，做事勤俭，一生福禄无缺，与人干事，反为不美，六亲骨肉可靠，交朋友，四海春风，中限光耀门庭，见善不欺，逢恶不怕，事有始终，量能宽大，义利分明，吉人天相，四海闻名，末限成家立业，安然到老，高楼大厦，妻宫无刑，子息三人，只一子送终，寿元七十七，卒于春光中。"},
            new String[]{"三两七钱",   "此命般般事不成、弟兄少力自孤行。\n" + "虽然祖业须微有，来得明时去不明。\n"    ,"一生财来复去，难得大富之命,此命为人品性刚直，做事公开有才能，不肯休息，六亲兄弟不得力，祖业无靠，白手成家立业，末运多驳杂，不能聚财，不欺负人，有义气，心神不定，易成喜怒，初限奔波劳苦，离别他境可成家计，改换门庭，中限未得如意，末限环环妻宫，方可刑克，子息虽有不得力，只好真假送终，寿元七十七，死于七月中。"},
            new String[]{"三两八钱",   "一身骨肉最清高，早入簧门姓氏标。\n" + "待到年将三十六，蓝衫脱去换红袍。\n"    ,"此命为人品性刚直，做事公开有才能，不肯休息，六亲兄弟不得力，祖业无靠，白手成家立业，末运多驳杂，不能聚财，好一双抓钱手，没有一个赚钱斗，此命蜘蛛结网，朝圆夜不圆，做几番败几番，只能稳步成家计，谁知又被狂风吹，初限二十三四，犹如明月被云侵，三十外来恰是日头又重开，终交末运方为贵，渐渐荣昌盛。"},
            new String[]{"三两九钱",   "此命终身运不通，劳劳作事尽皆空，\n" +  "苦心竭力成家计，到得那时在梦中。\n"    ,"此命为人品性刚直，做事公开有才能，有机变不肯休息，六亲兄弟不得力，祖业无靠，白手成家立业，末运多驳杂，不能聚财，好一双抓钱手，没有一个赚钱斗，此命蜘蛛结网，朝圆夜不圆，做几番败几番，只能稳步成家计，谁知又被狂风吹，初限二十三四，犹如明月被云侵，三十外来恰是日头又重开，二子送终，寿元五十七岁，过此八十八，死于秋天中。"},
            new String[]{"四两",   "平生衣禄是绵长，件件心中自主张。\n" + "前面风霜多受过，后来必定享安康。\n"   ,"此命为人性躁，心直口快，有才能，逢善不欺，逢恶不怕，事有始终，量能宽大，不能聚财，祖业破败，兄弟六亲不得力，自立家计出外方好，初限二十五六连年不遂，二十七岁有好运，犹如枯木逢春，中限四十九岁有灾，铁镜重磨，明月正圆，五十六七交大运，寿元七十七，卒于春光中。"},
            new String[]{"四两一钱",   "此命推来事不同，为人能干异凡庸，\n" + "中年还有逍遥福，不比前时运未通。\n"    ,"此命性重气高，有口无心，祖业未交，离别他境，事事可成，六亲骨肉不得力，自成家计，学习经营，四方闻名，当把外方之时，丰隆初限奔波驳杂，不能聚财，交过三十八九方可成家，四十五六方能顺意，末限犹如三月杨柳，枝枝生细叶，晚景处处红，妻宫无克破，子息假送老，寿元四十七，闯过可到六十六，卒于九月中。"},
            new String[]{"四两二钱",   "得宽怀处且宽怀，何用双眉皱不开，\n" + "若使中年命运济，那时名利一齐来。\n"    ,"此命为人操劳，自成自立，与人出力事不成，离祖之命，成家三番四次，用尽心机不得开怀，若要安乐享福，要到三十六到四十六时不谋自待，福不求自至，有贵人助力，家庭安然，妻宫若要无刑，猴、猪、羊、蛇不可配，龙、虎、马、牛方得安，兄弟六亲如冰碳，在家不得安然，初限驳杂多端，不能聚钱，常有忧愁，寿元七十八岁，死于三月中。"},
            new String[]{"四两三钱",   "为人心性最聪明，作事轩昂近贵人，\n" + "衣禄一生天数定，不须劳碌过平生。\n"    ,"此命为人性躁刚强，平生不受亏，多技多能，祖业冰碳，能聚财，交过三十开外，方得开怀，中限之命能进四方之财，出外逢贵人之力，艺术精，善经营，方能兴旺，上业迟，有一疾相侵，直至末限方得享福，妻宫匹配，龙虎马牛可配，二子送终，寿元八十，卒于四月之中。"},
            new String[]{"四两四钱",  "万事由天莫苦求，须知福禄命里收，\n" + "少壮名利难如意，晚景欣然更不忧。\n"    ,"此命为人忠直敬重，心慈性躁，深谋远虑，心中多劳，贵人钦敬，六亲冰碳，初限行运，美中不足，中限渐入佳境，名利可佳，刚柔有济，二十九交佳运，可通花甲，天赐麒麟送老，寿元八十五岁，卒于冬月之中。"},
            new String[]{"四两五钱",   "名利推来竟若何，前番辛苦后奔波。\n"  +  "命中难养男与女，骨肉扶持也不多。\n"     ,"此命为人品性不刚不柔，心中无毒，自当自担，离祖之命，做事有始有终，池塘鸳鸯觅食，或聚或散，骨肉六亲不得力，如嗥如风，劳心费力多成败，初限运寒多驳杂，祖业破败，重新白手成家，至三十五六方能成家立业，四十开外，如船遇顺风，五十多岁安稳，末限滔滔事业兴，妻宫硬配，子息伴架送终，寿元七十五岁，卒于五月之中。"},
            new String[]{"四两六钱",    "东西南北尽皆通，出姓移居更觉隆，\n" +  "衣禄无亏天数定，中年晚景一般同。\n"      ,"此命为人心慈性躁，有口无心，有粗有细，一生奔波，六亲无靠，无大难，妻宫无刑，祖业凋零，自立家计，早业如同败落萍，劳心用下一半生，交三十五六七八岁，又平平度过几春秋，六十前后花开日，花开又招雨来淋，必定小人加暗害，平日之中要小心，早子招维，只一子送终，寿元七十三，卒于冬月之中"},
            new String[]{"四两七钱",    "此命推为旺末年，妻荣子贵自怡然，\n" + "平生原有滔滔福，财源滚滚似水流。\n"      ,"此命为人品性纯和，做事公道，忠心待人气质高，与人干事恩仇报，兄弟不力祖业微，早年驳杂多端，时来骨肉精，财源是归命，匹马单枪，初限运来二十至三十岁，末限交运都好，反到交时苦衰，三十六至四十来岁，犹如金秋菊遇秋开放，心机用尽方为贵，末运交来怡称怀，祖业有破，家业重注，好似枯木逢春再开花，孤子送老，五十九岁有一限到六十九岁，寿元八十二卒于冬月之中。"},
            new String[]{"四两八钱",   "初年运道未曾亨，若是蹉跎再不兴，\n" +  "兄弟六亲皆无靠，一身事业晚年成。\n"      ,"此命为人性躁，能随机应变，常近贵人，祖业无成，骨肉六亲少义，一个自立家计，初限交来财运如霜雪，中限略可成家，大运突来能立家业，妻有克，小配无刑，子息三人，寿元七十七岁，卒于七月之中。"},
            new String[]{"四两九钱",    "此命推来福不轻，自成自立显门庭。\n" + "从来富贵人钦敬，使婢差奴过一生。\n"    ,"此命为人品性纯和，做事勤俭，恩中招怨，兄弟有克，亲朋相援，赔酒赔饭，反说不美，初限贫愁，交过二十六七岁，如逆水行舟，不能聚财，中限驳杂多端，刑妻克子，交过四十岁，方可成家立业，般般遂意，件件称心，至四十七八岁有一灾，宁可损财交过，后有十年好运来，家中钱财聚，三子送老，寿元七十三岁，卒于九月之中。"},
            new String[]{"五两",    "为利为名终日劳，中年福禄也多遭，\n" + "老来是有财星照，不比前番目下高。\n"    ,"此命为人正直，伶俐灵巧，有机变，平生无大难，祖业无靠，自成自立，白手成家，亲朋冷落，兄弟少力，可得四方之财，好一双挣钱手，没有一个聚钱斗，满面春风人道好，一生不足自爱知，妻迟子晚，初限奔波，中限四十岁方交大运，犹如枯木逢春，四十九岁有一灾，其年福星高照，有十年大运，财禄丰盈大吉昌，妻宫铁硬同偕老，子息一双可送终，寿元六十九岁，卒于冬月之中。"},
            new String[]{"五两一钱",    "一世荣华事事通，不须劳碌自亨通，\n"  + "弟兄叔侄皆如意，家业成时福禄宏。\n"    ,"此命为人做事有能力，且能随机应变，性燥能知其轻重，交朋结友如兄弟，气量宽宏，见善不欺，逢恶不怕，平生正直，无大难刑险，只是少招祖业，初限衣禄无亏，子息晚招可实得，四十至五十，末限通达昌吉，福禄无亏，财源稳定，丰衣足食，高堂大厦，妻宫友好，二子两女送终，寿元八十岁，卒于九月中。"},
            new String[]{"五两二钱",     "一世荣华事事能，不须劳思自然宁，\n" + "宗族欣然心皆好，家业丰亨自称心。\n"    ,"此命为人多才多能，心机灵变，祖业飘零，离乡可成家计，兄弟少力，驳杂多端，为人只是救人无功，重义轻财，财禄易聚易散，早年聚财凡事顺意，三十至四十岁如意称心，末限福如东海，寿比南山，只是妻克两硬无刑，有三子二女送终，寿元八十三，卒于冬月之中。"},
            new String[]{"五两三钱",    "此格推为气量真，兴家发达在其中，\n" +  "一生福禄安排定，却是人间一富翁。\n"     ,"此命推来敬重双亲，有福有禄，气质高昂，少年勤学功名不就，忠孝两全，心善无毒，非富则贵，出外有人钦佩，四海名扬，到老荣华，限上无忧，一世健康，青年欠利，末限安享福禄，白鹤先生云：此骨三限之骨，子孙王相之局，初限早成家计，辛勤劳苦，中限渐渐生财，重整门庭，末限荣华富贵，妻宫小配无刑，有三子二女送终，寿元八十二，卒于冬月之中。"},
            new String[]{"五两四钱",     "此命推来厚且清，诗书满腹看功成，\n" + "丰衣足食自然稳，正是人间有福人。\n"      ,"此命为人灵巧，胸襟通达，志气高强，少年勤学有功名，年轻欠利，腹中多谋，有礼有义，有才有能，做事勤俭，一生福禄无亏，与人干事反为不美，亲朋戚友，四海春风。中限光辉门庭，逢善不欺，逢恶不怕，事有始终，吉人天相，四海扬名，成家立业，安然到老，高楼大厦，妻宫硬无刑，子息三人，只一子送终，寿元七十七，卒于春光中。"},
            new String[]{"五两五钱",    "走马扬鞭争利名，少年作事费筹论，\n" +  "一朝福禄源源至，富贵荣华显六亲。\n"      ,"此命为人灵巧机巧，初限尚不聚财，只是虚名虚利，财来财去，一生勤于学，自有功名，有衣禄，福星照命，中限交来可称心，求谋如意，出外有人恭敬，一生受贵，要问其他消息，事后兴家发达，壮年滔滔财源旺，滚滚利顺来，迎春花正发，微风细雨生，四十九交来末运，移花接木桂花香，夫妻百年同偕老，寿元八十之外，福禄荣昌，卒于春光之中。"},
            new String[]{"五两六钱",    "此格推来礼义通，一身福禄用无穷，\n" +  "甜酸苦辣皆尝过，滚滚财源稳且丰。\n"      ,"此命为人性巧心灵，有口无心，事不保密，少年劳碌难免，志在四方，身心健康，前运乘阴少种树，怀才不遇，中限轻财，大举随行，移动得安然终日成，名声可望，旧业换新，名利享通，五人盆石皆白发，倾自心田此后昆，此命小事宜放松，方有子息，寿元八十二岁，卒于冬月之中。"},
            new String[]{"五两七钱",     "福禄丰盈万事全，一身荣耀乐天年。\n" + "名扬威震人争羡，此世逍遥宛似仙。\n"    ,"此命为人心灵性巧，做事细致，足智多谋，志气高昂，少年勤学，名利成就，逍遥快乐，气量宽宏，财禄有余，犹如锦上添花，中限以来，自成自立，渐渐荣昌，招人进财，妻子晚配为美，四十至四十五六岁，看子成名，末限多得意，家中财产甚丰隆，妻宫无克，二子送终，寿元七十三岁，卒于正月中。"},
            new String[]{"五两八钱",     "平生福禄自然来，名利兼全福寿偕，\n" + "雁塔题名为贵客，紫袍金带走金阶。\n"     ,"此命为人忠直，做事有头有尾，身清气高，六亲有旺，兄弟少帮，妻宫并重，子息二三，他乡创业，官臣之命，只是与人干事，恩中招怨，反为不美，早限财来财去，中限兴旺，一子送终，寿元八十三岁，卒于四月之中。"},
            new String[]{"五两九钱",     "细推此格妙且清，必定才高礼义通，\n" +"甲第之中应有分，扬鞭走马显威荣。\n"      ,"此命为人性情暴躁，刚强，平生不受亏，所谓量大多智多能，受人尊敬，祖业凋零，兄弟只可画饼充饥，亲戚则是望梅止渴，劳心见早，发福见迟，独立成家，只是早聚财，逢凶化吉，驳杂交过二十开外，方得顺利开怀，中限之命可进四方之财，出外有贵人助力，可精手艺营业，方能兴家立业，此间或有小疾相侵，再交限方得安然，坐享福禄，妻宫之配龙虎马牛，一子送老，寿元八十岁，卒于六月之中。"},
            new String[]{"六两",    "一朝金榜快题名，显祖荣宗立大功，\n" +"衣禄定然原裕足，田园财帛更丰盈。\n"       ,"此命为人灵机性巧，胸襟发达，志气高强，少年勤学，有功名之格，青年欠利，腹中多谋，有礼有仪，有才能，做事勤俭，一生福禄无亏，与人做事，有力无功，兄弟骨肉中多谋，交朋友，四海名扬，中限光辉门户，早能发达，义利分明，末限成家立业安然到老，高楼大厦，妻宫两硬无刑，子息三人，只有一人送终，寿元七十七岁，卒于春光之中。"},
            new String[]{"六两一钱",     "不作朝中金榜客，定为世上大财翁，\n" +"聪明天赋经书熟，名显高科自是荣。\n"       ,"此命为人心秉直，聪明利达，心善口快，有才能。见善不欺，逢恶不怕，刚柔有济，事有始终，早能宽大，而能聚财，祖业如旧，六亲兄弟有靠，自立家计出外更好，二十至二十五六七八岁有险，三十开外古镜重磨，明月再圆，六十六至七十方交大运妻宫小配，寿元七十七岁，卒于春光之中。"},
            new String[]{"六两二钱",     "此命生来福不穷，读书必定显亲宗，\n" + "紫衣金带为卿相，富贵荣华皆可同。\n"        ,"此命为人忠直敦厚，心无所毒，性巧灵敏，深谋远虑，吉人天相，心中多劳，受人钦叹，美中不足，中限渐入佳境，名利可佳，刚济有情，二十九交来阳春暖，东北佳音，天津四通，花甲一二岁大顺，天赐麒麟送老，寿元八十五岁，卒于冬月之中。"},
            new String[]{"六两三钱",      "命主为官福禄长，得来富贵实丰常，\n" + "名题金塔传金榜，定中高科天下扬。\n"     ,"此命为人聪明利达，近知识，远小人，自觉性强，改悔及时，君子量大，福禄寿三星拱照，富贵名扬天下，荣宗显祖之格，可是美中欠佳，妻宫有硬，操劳心重，先天下之忧而忧，后天下之乐而乐，寿元七十有八，享于荣绵归期，二子二女送终。"},
            new String[]{"六两四钱",       "此格威权不可当，紫袍金带尘高堂。\n" + "荣华富贵谁能及？积玉堆金满储仓。\n"     ,"此命为权威大官，万古留名之富贵命"},
            new String[]{"六两五钱",       "细推此命福非轻，富贵荣华孰与争？\n" + "定国安邦人极品，威声显赫震寰瀛。\n"     ,"大富大贵，堆金积玉之福命"},
            new String[]{"六两六钱",      "此格人间一福人，堆金积玉满堂春，\n" + "从来富贵由天定，正笏垂绅谒圣君。\n"     ,"大富大贵，堆金积玉之福命"},
            new String[]{"六两七钱",      "此命生来福自宏，田园家业最高隆，\n" +  "平生衣禄盈丰足，一世荣华万事通。\n"     ,"一世荣华，享福无边之命"},
            new String[]{"六两八钱",      "富贵由天莫苦求，万金家计不须谋，\n" +   "如今不比前翻事，祖业根基飞古留。\n"     ,"享受天赐之福，近贵显达之命"},
            new String[]{"六两九钱",       "君是人间衣禄星，一生富贵众人钦，\n" +   "纵然福禄由天定，安享荣华过一生。\n"     ,"祖业虽多，若不紧守也会落空"},
            new String[]{"七两",       "此命推来福禄宏，不须愁虑苦劳心，\n" +   "一生天定衣与禄，富贵荣华主一生。\n"     ,"一生清荣，富贵双全之命荣华富贵已天定，正笏垂绅拜紫宸。"},
            new String[]{"七两一钱",      "此命生成大不同，公侯卿相在其中。\n" +    "一生自有逍遥福，富贵荣华极品隆。\n"     ,"一生清荣，富贵双全之命荣华富贵已天定，正笏垂绅拜紫宸。"},
            new String[]{"七两二钱",     "此格世界罕有生，十代积善产此人。\n" +    "天上紫微来照命，统治万民乐太平。\n"     ,""},
    };

    @Override
    public WeightInfo WeightMe(PersonInfo personInfo) {

        List<String> eight = personInfo.getEightWord();
        String year = eight.get(0) + eight.get(1);
        String mouth = eight.get(3);
        String day = personInfo.getChineseDay();
        String hour = eight.get(7);

        Double sumWeight = 0.0;
        //年的重量
        for (int i = 0; i < JIA_ZI.length; i++) {
            if (year.equals(JIA_ZI[i])) {
                sumWeight = YEAR_WEIGHT[i];
                break;
            }
        }
        //月的重量
        for (int i = 0; i < Zhi.length; i++) {
            if (mouth.equals(Zhi[i])) {
                sumWeight += Mouth_WEIGHT[i];
                break;
            }
        }
        //日的重量
        for (int i = 0; i < DAY_CH.length; i++) {
            if (day.equals(DAY_CH[i])) {
                sumWeight += DAY_WEIGHT[i];
                break;
            }
        }
        //时的重量
        for (int i = 0; i < Zhi.length; i++) {
            if (hour.equals(Zhi[i])) {
                sumWeight += HOUR_WEIGHT[i];
                break;
            }
        }
        Long result = Math.round((sumWeight - 2.1) * 10);
        String[] weight= (String[])resultWeight[result.intValue()];
        WeightInfo weightInfo=new WeightInfo();
        weightInfo.setWeight(weight[0]);
        weightInfo.setPoem(weight[1]);
        weightInfo.setDetail(weight[2]);
        List<String> e=personInfo.getEightWord();
        weightInfo.setEightWord(e.get(0)+" "+ e.get(2)+" "+ e.get(4)+" "+ e.get(6)+"\n"+ e.get(1)+" "+ e.get(3)+" "+ e.get(5)+" "+ e.get(7));
        return weightInfo;
    }
}
