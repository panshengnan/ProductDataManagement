package com.cgwx.dao;

import com.cgwx.data.entity.PdmThemeticProductIndustryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PdmThemeticProductIndustryInfoMapper {
    int insert(PdmThemeticProductIndustryInfo record);

    List<PdmThemeticProductIndustryInfo> selectAll();
}