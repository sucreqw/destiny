package com.sucre.destiny.service;

import com.sucre.destiny.info.DeityInfo;
import com.sucre.destiny.info.PersonInfo;

import java.util.List;

public interface DeityService {
    List<DeityInfo> allDeity(PersonInfo personInfo);
}
