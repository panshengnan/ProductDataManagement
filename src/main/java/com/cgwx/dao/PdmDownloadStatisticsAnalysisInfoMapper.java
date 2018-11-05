package com.cgwx.dao;

import com.cgwx.data.entity.PdmDownloadStatisticsAnalysisInfo;
import java.util.List;

public interface PdmDownloadStatisticsAnalysisInfoMapper {
    int insert(PdmDownloadStatisticsAnalysisInfo record);

    List<PdmDownloadStatisticsAnalysisInfo> selectAll();
}