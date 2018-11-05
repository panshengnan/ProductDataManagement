package com.cgwx.dao;

import com.cgwx.data.entity.PdmOrderProductInfo;
import java.util.List;

public interface PdmOrderProductInfoMapper {
    int insert(PdmOrderProductInfo record);

    List<PdmOrderProductInfo> selectAll();
}