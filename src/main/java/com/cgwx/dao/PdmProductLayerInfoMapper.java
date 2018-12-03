package com.cgwx.dao;

import com.cgwx.data.entity.PdmProductLayerInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PdmProductLayerInfoMapper {
    int insert(PdmProductLayerInfo record);

    List<PdmProductLayerInfo> selectAll();
}