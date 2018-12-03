package com.cgwx.dao;

import com.cgwx.data.entity.PdmAdvancedProductShpInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PdmAdvancedProductShpInfoMapper {
    int insert(PdmAdvancedProductShpInfo record);

    List<PdmAdvancedProductShpInfo> selectAll();
}