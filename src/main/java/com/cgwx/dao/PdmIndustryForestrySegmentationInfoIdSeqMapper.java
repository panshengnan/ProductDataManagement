package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryForestrySegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryForestrySegmentationInfoIdSeqMapper {
    int insert(PdmIndustryForestrySegmentationInfoIdSeq record);

    List<PdmIndustryForestrySegmentationInfoIdSeq> selectAll();
}