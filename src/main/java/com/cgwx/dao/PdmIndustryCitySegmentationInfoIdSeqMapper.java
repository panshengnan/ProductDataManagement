package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryCitySegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryCitySegmentationInfoIdSeqMapper {
    int insert(PdmIndustryCitySegmentationInfoIdSeq record);

    List<PdmIndustryCitySegmentationInfoIdSeq> selectAll();
}