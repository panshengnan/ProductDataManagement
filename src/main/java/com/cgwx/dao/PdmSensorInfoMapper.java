package com.cgwx.dao;

import com.cgwx.data.entity.PdmSensorInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PdmSensorInfoMapper {
    int insert(PdmSensorInfo record);

    List<PdmSensorInfo> selectAll();

    @Select("SELECT sensor_description\n" +
            "FROM pdm_sensor_info\n" +
            "WHERE 1=1 order by gmt_created asc "
    )
    List<String> selectSensorInfo();
}