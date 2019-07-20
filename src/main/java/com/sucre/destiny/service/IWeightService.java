package com.sucre.destiny.service;

import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.info.PersonInfo;
import com.sucre.destiny.info.WeightInfo;
import org.springframework.stereotype.Service;


public interface IWeightService {
    WeightInfo WeightMe(PersonInfo personInfo);
}
