package com.cgwx.dao;

import com.cgwx.data.entity.PdmArchiveRecordsInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PdmArchiveRecordsInfoMapper {
    int insert(PdmArchiveRecordsInfo record);

    List<PdmArchiveRecordsInfo> selectAll();

    @Select({"select *\n" +
            "from pdm_archive_records_info\n" +
            "where archive_personnel =  #{archivePersonnel} "})
    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "product_name",
            property = "productName"
    ), @Result(
            column = "archive_time",
            property = "archiveTime"
    ), @Result(
            column = "archive_type",
            property = "archiveType"
    ), @Result(
            column = "archive_personnel",
            property = "archivePersonnel"
    ),@Result(
            column = "archive_result",
            property = "archiveResult"
    )})
    List<PdmArchiveRecordsInfo> selectArchiveRecordsByArchivePersonnel(@Param("archivePersonnel") String archivePersonnel);

    @Select({" update pdm_archive_records_info\n" +
            "set archive_result = #{result} , product_id = #{productId}\n" +
            "where product_id = #{tempId} "})
   void updateArchiveRecordsInfo(@Param("productId") String productId,@Param("result") int result,@Param("tempId") String tempId);
}