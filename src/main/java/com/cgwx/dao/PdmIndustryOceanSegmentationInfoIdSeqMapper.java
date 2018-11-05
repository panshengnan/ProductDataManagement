package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryOceanSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryOceanSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryOceanSegmentationInfoIdSeq record);

    List<PdmIndustryOceanSegmentationInfoIdSeq> selectAll();
}