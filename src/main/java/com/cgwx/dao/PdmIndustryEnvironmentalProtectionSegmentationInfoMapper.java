package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryEnvironmentalProtectionSegmentationInfo;
import java.util.List;

public interface PdmIndustryEnvironmentalProtectionSegmentationInfoMapper {
    int insert(PdmIndustryEnvironmentalProtectionSegmentationInfo record);

    List<PdmIndustryEnvironmentalProtectionSegmentationInfo> selectAll();
}