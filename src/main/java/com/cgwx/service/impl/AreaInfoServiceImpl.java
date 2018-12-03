package com.cgwx.service.impl;

import com.cgwx.data.dto.AreaInfoDto;
import com.cgwx.data.dto.CountryInfo;
import com.cgwx.service.IAreaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cgwx.dao.*;
import java.io.IOException;
import java.util.List;

@Service
public class AreaInfoServiceImpl implements IAreaInfoService {

    @Autowired
    PdmDomesticAreaInfoMapper pdmDomesticAreaInfoMapper;
    @Autowired
    PdmCountryAreaInfoMapper pdmCountryAreaInfoMapper;
    @Override
    public List<AreaInfoDto> getAreaListByParentId(Integer parentId) throws IOException {
        return pdmDomesticAreaInfoMapper.selectAllChild(parentId);
    }
    @Override
    public List<CountryInfo> getCountryList()
    {
        return pdmCountryAreaInfoMapper.selectAllcountry();
    }
}
