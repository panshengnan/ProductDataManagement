package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryNaturalCalamitiesSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryNaturalCalamitiesSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryNaturalCalamitiesSegmentationInfoIdSeq record);

    List<PdmIndustryNaturalCalamitiesSegmentationInfoIdSeq> selectAll();
}