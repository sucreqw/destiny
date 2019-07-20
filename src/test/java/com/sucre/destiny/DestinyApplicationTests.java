package com.sucre.destiny;

import com.sucre.destiny.common.LunarTerm;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.service.impl.WeightImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DestinyApplicationTests {

    @Test
    public void contextLoads() {
        Double sumWeight=2.20000000004;
        Long result=Math.round((sumWeight-2.1)*10);
        System.out.println(result);
    }

}
