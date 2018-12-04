package com.cgwx.dao;

import com.cgwx.data.entity.PdmArchiveCheckStatusInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PdmArchiveCheckStatusInfoMapper {
    int insert(PdmArchiveCheckStatusInfo record);

    List<PdmArchiveCheckStatusInfo> selectAll();
}