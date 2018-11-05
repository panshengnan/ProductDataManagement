package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryOthersSegmentationInfo;
import java.util.List;

public interface PdmIndustryOthersSegmentationInfoMapper {
    int insert(PdmIndustryOthersSegmentationInfo record);

    List<PdmIndustryOthersSegmentationInfo> selectAll();
}