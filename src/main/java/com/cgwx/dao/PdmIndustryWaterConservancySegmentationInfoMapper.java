package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryWaterConservancySegmentationInfo;
import java.util.List;

public interface PdmIndustryWaterConservancySegmentationInfoMapper {
    int insert(PdmIndustryWaterConservancySegmentationInfo record);

    List<PdmIndustryWaterConservancySegmentationInfo> selectAll();
}