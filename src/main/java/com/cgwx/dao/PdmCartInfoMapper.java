package com.cgwx.dao;

import com.cgwx.data.entity.PdmCartInfo;
import java.util.List;

public interface PdmCartInfoMapper {
    int insert(PdmCartInfo record);

    List<PdmCartInfo> selectAll();
}