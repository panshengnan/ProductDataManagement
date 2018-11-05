package com.cgwx.dao;

import com.cgwx.data.entity.PdmSpatialRefSys;
import java.util.List;

public interface PdmSpatialRefSysMapper {
    int deleteByPrimaryKey(Integer srid);

    int insert(PdmSpatialRefSys record);

    PdmSpatialRefSys selectByPrimaryKey(Integer srid);

    List<PdmSpatialRefSys> selectAll();

    int updateByPrimaryKey(PdmSpatialRefSys record);
}