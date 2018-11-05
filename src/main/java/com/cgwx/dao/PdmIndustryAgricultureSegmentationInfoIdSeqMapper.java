package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryAgricultureSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryAgricultureSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryAgricultureSegmentationInfoIdSeq record);

    List<PdmIndustryAgricultureSegmentationInfoIdSeq> selectAll();
}