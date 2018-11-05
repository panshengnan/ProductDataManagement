package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryForestrySegmentationInfo;
import java.util.List;

public interface PdmIndustryForestrySegmentationInfoMapper {
    int insert(PdmIndustryForestrySegmentationInfo record);

    List<PdmIndustryForestrySegmentationInfo> selectAll();
}