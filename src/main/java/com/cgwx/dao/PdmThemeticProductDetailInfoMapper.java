package com.cgwx.dao;

import com.cgwx.data.entity.PdmThemeticProductDetailInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PdmThemeticProductDetailInfoMapper {
    int insert(PdmThemeticProductDetailInfo record);

    List<PdmThemeticProductDetailInfo> selectAll();
}