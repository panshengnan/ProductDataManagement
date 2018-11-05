package com.cgwx.dao;

import com.cgwx.data.entity.PdmOrderInfo;
import java.util.List;

public interface PdmOrderInfoMapper {
    int insert(PdmOrderInfo record);

    List<PdmOrderInfo> selectAll();
}