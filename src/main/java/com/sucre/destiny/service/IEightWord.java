package com.sucre.destiny.service;

import com.sucre.destiny.dto.PersonDTO;
import com.sucre.destiny.info.PersonInfo;

import java.util.Calendar;

public interface IEightWord {
    PersonInfo time2Person(Boolean isLeap,Boolean isChinese,PersonDTO personDTO);
}
