package com.cgwx.dao;

import com.cgwx.data.entity.PdmThemeticProductDetailIndustryInfoIdSeq;
import java.util.List;

public interface PdmThemeticProductDetailIndustryInfoIdSeqMapper {
    int insert(PdmThemeticProductDetailIndustryInfoIdSeq record);

    List<PdmThemeticProductDetailIndustryInfoIdSeq> selectAll();
}