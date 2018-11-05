package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryTopographicFeaturesSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryTopographicFeaturesSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryTopographicFeaturesSegmentationInfoIdSeq record);

    List<PdmIndustryTopographicFeaturesSegmentationInfoIdSeq> selectAll();
}