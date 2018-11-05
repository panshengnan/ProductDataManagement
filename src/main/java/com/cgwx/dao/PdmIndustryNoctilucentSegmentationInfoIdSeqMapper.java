package com.cgwx.dao;

import com.cgwx.data.entity.PdmIndustryNoctilucentSegmentationInfoIdSeq;
import java.util.List;

public interface PdmIndustryNoctilucentSegmentationInfoIdSeqMapper {
    int insert(PdmIndustryNoctilucentSegmentationInfoIdSeq record);

    List<PdmIndustryNoctilucentSegmentationInfoIdSeq> selectAll();
}