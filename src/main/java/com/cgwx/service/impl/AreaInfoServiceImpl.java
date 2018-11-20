package com.cgwx.service.impl;

import com.cgwx.dao.PdmDomesticAreaInfoMapper;
import com.cgwx.data.dto.AreaInfoDto;
import com.cgwx.data.entity.PdmDomesticAreaInfo;
import com.cgwx.service.IAreaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AreaInfoServiceImpl implements IAreaInfoService {

    @Autowired
    com.cgwx.dao.PdmDomesticAreaInfoMapper PdmDomesticAreaInfoMapper;

    @Override
    public List<AreaInfoDto> getAreaListByParentId(Integer parentId) throws IOException {
        return PdmDomesticAreaInfoMapper.selectAllChild(parentId);
    }

}
