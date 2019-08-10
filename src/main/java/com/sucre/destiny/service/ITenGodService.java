package com.sucre.destiny.service;

import com.sucre.destiny.dto.TenGodDTO;
import com.sucre.destiny.info.TenGodInfo;

import java.util.List;

public interface ITenGodService {
    TenGodInfo eightToGod(TenGodDTO tenGodDTO);

    List<String> listToGod(List<String> gen, String dayGen);
}
