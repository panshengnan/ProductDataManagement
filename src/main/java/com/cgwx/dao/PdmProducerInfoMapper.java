package com.cgwx.dao;

import com.cgwx.data.entity.PdmProducerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PdmProducerInfoMapper {
    int insert(PdmProducerInfo record);

    List<PdmProducerInfo> selectAll();

    @Select("SELECT count(producer)\n" +
            "FROM pdm_producer_info\n" +
            "WHERE producer like  '${producerName}' "
    )
    int selectCountByProducerName(@Param("producerName") String producerName);


    @Select("SELECT producer\n" +
            "FROM pdm_producer_info\n" +
            "WHERE producer like  '%${producer}%' and producer <> '' order by producer collate \"C\" "
    )
    List<String> selectProducerList(@Param("producer") String producer);

}