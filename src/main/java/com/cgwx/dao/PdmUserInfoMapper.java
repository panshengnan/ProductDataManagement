package com.cgwx.dao;

import com.cgwx.data.entity.PdmUserInfo;
import java.util.List;

public interface PdmUserInfoMapper {
    int insert(PdmUserInfo record);

    List<PdmUserInfo> selectAll();
}