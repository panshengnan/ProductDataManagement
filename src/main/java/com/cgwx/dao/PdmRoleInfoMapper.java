package com.cgwx.dao;

import com.cgwx.data.entity.PdmRoleInfo;
import java.util.List;

public interface PdmRoleInfoMapper {
    int insert(PdmRoleInfo record);

    List<PdmRoleInfo> selectAll();
}