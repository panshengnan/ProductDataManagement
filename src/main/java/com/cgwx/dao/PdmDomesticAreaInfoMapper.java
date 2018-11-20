package com.cgwx.dao;

import com.cgwx.data.dto.AreaInfoDto;
import com.cgwx.data.entity.PdmDomesticAreaInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PdmDomesticAreaInfoMapper {
    int insert(PdmDomesticAreaInfo record);

    List<PdmDomesticAreaInfo> selectAll();

    @Select(" SELECT  area_id,area_name,ST_AsGeoJSON(area_geo)as area_geojson FROM pdm_domestic_area_info WHERE parent_area_id=#{parentId} order by area_name")
    @Results(value = {
            @Result( column="area_id",property="areaId" ),
            @Result(column="area_name", property="areaName"),
            @Result(column="area_geojson" ,property="areaGeojson")
    })
    List<AreaInfoDto>selectAllChild(Integer parentId);
}