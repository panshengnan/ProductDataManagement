package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryNoctilucentSegmentationInfo;
import java.util.List;

public interface PdmIndustryNoctilucentSegmentationInfoMapper {
    int insert(PdmIndustryNoctilucentSegmentationInfo record);

    List<PdmIndustryNoctilucentSegmentationInfo> selectAll();
}