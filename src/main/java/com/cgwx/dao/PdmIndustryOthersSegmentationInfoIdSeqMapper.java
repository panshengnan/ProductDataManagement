package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryOthersSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryOthersSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryOthersSegmentationInfoIdSeq record);

    List<PdmIndustryOthersSegmentationInfoIdSeq> selectAll();
}