package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryEnvironmentalProtectionSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryEnvironmentalProtectionSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryEnvironmentalProtectionSegmentationInfoIdSeq record);

    List<PdmIndustryEnvironmentalProtectionSegmentationInfoIdSeq> selectAll();
}