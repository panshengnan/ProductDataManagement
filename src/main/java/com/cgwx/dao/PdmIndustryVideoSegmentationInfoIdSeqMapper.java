package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryVideoSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryVideoSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryVideoSegmentationInfoIdSeq record);

    List<PdmIndustryVideoSegmentationInfoIdSeq> selectAll();
}