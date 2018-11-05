package com.cgwx.dao;

import com.cgwx.data.entity.PdmThemeticProductDetailIndustryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PdmThemeticProductDetailIndustryInfoMapper {
    int insert(PdmThemeticProductDetailIndustryInfo record);

    List<PdmThemeticProductDetailIndustryInfo> selectAll();
}