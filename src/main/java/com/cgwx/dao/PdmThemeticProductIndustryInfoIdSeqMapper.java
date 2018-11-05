package com.cgwx.dao;

import com.cgwx.data.entity.PdmThemeticProductIndustryInfoIdSeq;
import java.util.List;

public interface PdmThemeticProductIndustryInfoIdSeqMapper {
    int insert(PdmThemeticProductIndustryInfoIdSeq record);

    List<PdmThemeticProductIndustryInfoIdSeq> selectAll();
}