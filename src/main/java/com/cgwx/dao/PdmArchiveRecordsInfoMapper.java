package com.cgwx.dao;

import com.cgwx.data.entity.PdmArchiveRecordsInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PdmArchiveRecordsInfoMapper {
    int insert(PdmArchiveRecordsInfo record);

    List<PdmArchiveRecordsInfo> selectAll();

    @Select({"<script>"+
            "select *\n" +
            "from pdm_archive_records_info\n" +
            "where archive_personnel =  #{archivePersonnel} "

            +"<if test=' (archiveType == -1)  &amp; (archiveStatus == -1) &amp; null!= productName &amp; !\"\".equals(productName)'>"

            + " and product_name like CONCAT('%',#{productName},'%') "
            + "</if>"

            +"<if test=' archiveStatus > 0 '>"
            + " and archive_result = #{archiveStatus} "
            + "</if>"

            +"<if test=' archiveType > 0 '>"
            + " and archive_type = #{archiveType} "
            + "</if>"

            +"\n order by gmt_created desc"



            +"</script>"})
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
    List<PdmArchiveRecordsInfo> selectArchiveRecordsByArchivePersonnel(@Param("archivePersonnel") String archivePersonnel,
                                                                       @Param("productName") String productName,
                                                                       @Param("archiveType") int archiveType,
                                                                       @Param("archiveStatus")int archiveStatus);

    @Select({" update pdm_archive_records_info\n" +
            "set archive_result = #{result} , product_id = #{productId} , archive_type = #{productType} \n" +
            "where product_id = #{tempId} "})
   void updateArchiveRecordsInfo(@Param("productId") String productId,@Param("result") int result,@Param("tempId") String tempId,@Param("productType") int productType);
}