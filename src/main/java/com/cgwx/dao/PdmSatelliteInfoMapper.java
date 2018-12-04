package com.cgwx.dao;

import com.cgwx.data.entity.PdmSatelliteInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PdmSatelliteInfoMapper {
    int insert(PdmSatelliteInfo record);

    List<PdmSatelliteInfo> selectAll();


    @Select("SELECT satellite_description\n" +
            "FROM pdm_satellite_info\n" +
            "WHERE 1=1 order by gmt_created asc "
    )
    List<String> selectSatelliteInfo();
}