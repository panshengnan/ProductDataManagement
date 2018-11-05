package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryVideoSegmentationInfo;
import java.util.List;

public interface PdmIndustryVideoSegmentationInfoMapper {
    int insert(PdmIndustryVideoSegmentationInfo record);

    List<PdmIndustryVideoSegmentationInfo> selectAll();
}