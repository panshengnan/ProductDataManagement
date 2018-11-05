package com.cgwx.dao;

import com.cgwx.data.entity.PdmPermissionInfo;
import java.util.List;

public interface PdmPermissionInfoMapper {
    int insert(PdmPermissionInfo record);

    List<PdmPermissionInfo> selectAll();
}