package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryTerritorySegmentationInfo;
import java.util.List;

public interface PdmIndustryTerritorySegmentationInfoMapper {
    int insert(PdmIndustryTerritorySegmentationInfo record);

    List<PdmIndustryTerritorySegmentationInfo> selectAll();
}