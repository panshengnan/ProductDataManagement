package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryAgricultureSegmentationInfo;
import java.util.List;

public interface PdmIndustryAgricultureSegmentationInfoMapper {
    int insert(PdmIndustryAgricultureSegmentationInfo record);

    List<PdmIndustryAgricultureSegmentationInfo> selectAll();
}