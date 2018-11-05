package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryCitySegmentationInfo;
import java.util.List;

public interface PdmIndustryCitySegmentationInfoMapper {
    int insert(PdmIndustryCitySegmentationInfo record);

    List<PdmIndustryCitySegmentationInfo> selectAll();
}