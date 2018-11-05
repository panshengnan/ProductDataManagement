package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryElectricPowerSegmentationInfo;
import java.util.List;

public interface PdmIndustryElectricPowerSegmentationInfoMapper {
    int insert(PdmIndustryElectricPowerSegmentationInfo record);

    List<PdmIndustryElectricPowerSegmentationInfo> selectAll();
}