package com.sucre.destiny;

import com.sucre.destiny.common.LunarTerm;
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
        LunarTerm  st = new LunarTerm();
        System.out.println(st.getTimeByTerm(1987,"立春"));

    }

}
