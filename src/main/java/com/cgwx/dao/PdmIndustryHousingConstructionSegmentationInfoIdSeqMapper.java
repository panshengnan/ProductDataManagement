package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryHousingConstructionSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryHousingConstructionSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryHousingConstructionSegmentationInfoIdSeq record);

    List<PdmIndustryHousingConstructionSegmentationInfoIdSeq> selectAll();
}