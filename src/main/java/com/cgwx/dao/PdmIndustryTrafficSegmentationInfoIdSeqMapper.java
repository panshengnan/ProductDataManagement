package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryTrafficSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryTrafficSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryTrafficSegmentationInfoIdSeq record);

    List<PdmIndustryTrafficSegmentationInfoIdSeq> selectAll();
}