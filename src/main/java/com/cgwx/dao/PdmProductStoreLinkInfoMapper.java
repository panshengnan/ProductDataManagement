//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.entity.PdmProductStoreLinkInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PdmProductStoreLinkInfoMapper {
    int insert(PdmProductStoreLinkInfo var1);

    List<PdmProductStoreLinkInfo> selectAll();

    @Select({"SELECT product_id, single_period_product_id, file_name, store_link\n                       FROM pdm_product_store_link_info\n                    WHERE product_id = #{productId} and single_period_product_id = #{singlePeriodProductId}"})
    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "single_period_product_id",
            property = "singlePeriodProductId"
    ), @Result(
            column = "file_name",
            property = "fileName"
    ), @Result(
            column = "store_link",
            property = "storeLink"
    )})
    List<PdmProductStoreLinkInfo> selectProductStoreLinksByProductIdAndsingleId(@Param("productId") String var1, @Param("singlePeriodProductId") String var2);

    @Select({"SELECT product_id, single_period_product_id, file_name, store_link\n                       FROM pdm_product_store_link_info\n                       WHERE product_id = #{productId}"})
    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "single_period_product_id",
            property = "singlePeriodProductId"
    ), @Result(
            column = "file_name",
            property = "fileName"
    ), @Result(
            column = "store_link",
            property = "storeLink"
    )})
    List<PdmProductStoreLinkInfo> selectProductStoreLinksByProductId(@Param("productId") String var1);

    @Select({"SELECT  store_link\n                       FROM pdm_product_store_link_info\n               WHERE product_id = #{productId} and file_name like '%.pdf'"})
    String selectProductAnalysisReporturl(@Param("productId") String var1);

    @Select({"SELECT  store_link\n                       FROM pdm_product_store_link_info\n               WHERE product_id = #{productId} and file_name like '%.zip'"})

    String selectProductAllfileDownloadurl(@Param("productId") String var1);
}
