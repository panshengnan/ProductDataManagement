package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryWaterConservancySegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryWaterConservancySegmentationInfoIdSeqMapper {
    int insert(PdmIndustryWaterConservancySegmentationInfoIdSeq record);

    List<PdmIndustryWaterConservancySegmentationInfoIdSeq> selectAll();
}