package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryOceanSegmentationInfo;
import java.util.List;

public interface PdmIndustryOceanSegmentationInfoMapper {
    int insert(PdmIndustryOceanSegmentationInfo record);

    List<PdmIndustryOceanSegmentationInfo> selectAll();
}