package com.cgwx.dao;

import com.cgwx.data.entity.PdmArchiveCheckStatusInfo;
import java.util.List;

public interface PdmArchiveCheckStatusInfoMapper {
    int insert(PdmArchiveCheckStatusInfo record);

    List<PdmArchiveCheckStatusInfo> selectAll();
}