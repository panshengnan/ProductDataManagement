package com.cgwx.service.impl;

import com.cgwx.data.dto.AreaInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class AreaInfoServiceImpl implements IAreaInfoService {
    @Autowired


    @Override
    public List<AreaInfoDto> getAreaListByParentId(Integer parentId) throws IOException {
        System.out.println("Service OK");
      
    }
}
