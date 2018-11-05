package com.cgwx.dao;

import com.cgwx.data.entity.PdmProductTypeInfo;
import java.util.List;

public interface PdmProductTypeInfoMapper {
    int insert(PdmProductTypeInfo record);

    List<PdmProductTypeInfo> selectAll();
}