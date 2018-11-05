package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryTopographicFeaturesSegmentationInfo;
import java.util.List;

public interface PdmIndustryTopographicFeaturesSegmentationInfoMapper {
    int insert(PdmIndustryTopographicFeaturesSegmentationInfo record);

    List<PdmIndustryTopographicFeaturesSegmentationInfo> selectAll();
}