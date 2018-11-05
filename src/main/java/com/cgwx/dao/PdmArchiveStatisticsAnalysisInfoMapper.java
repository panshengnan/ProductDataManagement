package com.cgwx.dao;

import com.cgwx.data.entity.PdmArchiveStatisticsAnalysisInfo;
import java.util.List;

public interface PdmArchiveStatisticsAnalysisInfoMapper {
    int insert(PdmArchiveStatisticsAnalysisInfo record);

    List<PdmArchiveStatisticsAnalysisInfo> selectAll();
}