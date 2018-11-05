package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryTrafficSegmentationInfo;
import java.util.List;

public interface PdmIndustryTrafficSegmentationInfoMapper {
    int insert(PdmIndustryTrafficSegmentationInfo record);

    List<PdmIndustryTrafficSegmentationInfo> selectAll();
}