package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryElectricPowerSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryElectricPowerSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryElectricPowerSegmentationInfoIdSeq record);

    List<PdmIndustryElectricPowerSegmentationInfoIdSeq> selectAll();
}