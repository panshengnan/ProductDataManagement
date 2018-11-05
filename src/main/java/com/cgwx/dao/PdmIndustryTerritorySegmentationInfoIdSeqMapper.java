package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryTerritorySegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryTerritorySegmentationInfoIdSeqMapper {
    int insert(PdmIndustryTerritorySegmentationInfoIdSeq record);

    List<PdmIndustryTerritorySegmentationInfoIdSeq> selectAll();
}