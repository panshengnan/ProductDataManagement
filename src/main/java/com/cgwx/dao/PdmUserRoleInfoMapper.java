package com.cgwx.dao;

import com.cgwx.data.entity.PdmUserRoleInfo;
import java.util.List;

public interface PdmUserRoleInfoMapper {
    int insert(PdmUserRoleInfo record);

    List<PdmUserRoleInfo> selectAll();
}