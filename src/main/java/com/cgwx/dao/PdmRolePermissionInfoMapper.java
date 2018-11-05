package com.cgwx.dao;

import com.cgwx.data.entity.PdmRolePermissionInfo;
import java.util.List;

public interface PdmRolePermissionInfoMapper {
    int insert(PdmRolePermissionInfo record);

    List<PdmRolePermissionInfo> selectAll();
}