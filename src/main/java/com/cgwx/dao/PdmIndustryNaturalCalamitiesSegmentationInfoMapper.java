package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryNaturalCalamitiesSegmentationInfo;
import java.util.List;

public interface PdmIndustryNaturalCalamitiesSegmentationInfoMapper {
    int insert(PdmIndustryNaturalCalamitiesSegmentationInfo record);

    List<PdmIndustryNaturalCalamitiesSegmentationInfo> selectAll();
}