package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryHousingConstructionSegmentationInfo;
import java.util.List;

public interface PdmIndustryHousingConstructionSegmentationInfoMapper {
    int insert(PdmIndustryHousingConstructionSegmentationInfo record);

    List<PdmIndustryHousingConstructionSegmentationInfo> selectAll();
}