//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.dto.ProductQueryList;
import com.cgwx.data.entity.PdmProductInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PdmProductInfoMapper {
    int insert(PdmProductInfo var1);

    List<PdmProductInfo> selectAll();

    @Select({"SELECT client_name\nFROM pdm_product_info\nWHERE client_name like  '%${clientName}%' order by client_name collate \"C\" "})
    List<String> selectClientNameList(@Param("clientName") String var1);

    @Select({"SELECT deliver_name\nFROM pdm_product_info\nWHERE deliver_name like  '%${deliverName}%' order by deliver_name collate \"C\" "})
    List<String> selectDeliverNameList(@Param("deliverName") String var1);

    @Select({"SELECT producer\nFROM pdm_product_info\nWHERE producer like  '%${producer}%' order by producer collate \"C\" "})
    List<String> selectProducerList(@Param("producer") String var1);

    @Select({"SELECT product_type\n" +
            " FROM pdm_product_info\n" +
            "WHERE product_id = #{productId}"
    })
    int selectProductTypeByProductId(@Param("productId") String productId);

    @Select({"SELECT product_description\n" +
            " FROM pdm_product_info\n" +
            "WHERE product_id = #{productId}"
    })
    String selectProductDescriptionByProductId(@Param("productId") String productId);

    @Select("<script>"
            +"SELECT  r.product_type, r.product_description, r.product_name, r.product_id, i.industry\n" +
            "FROM pdm_product_info  r , pdm_themetic_product_info i \n" +
            "WHERE  1=1 and r.product_id =i.product_id \n" +
            "<if test='null!= productName &amp; !\"\".equals(productName)'>"
            + "and r.product_name like CONCAT('%',#{productName},'%') "
            + "</if>"
            +"<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
            + "and r.product_description like CONCAT('%',#{productDescription},'%') "
            + "</if>"
            +"<if test='\"submitTimeAsc\".equals(orderby)'>"
            + "order by r.gmt_created asc"
            + "</if>"
            +"<if test='\"submitTimeDesc\".equals(orderby)'>"
            + "order by r.gmt_created desc"
            + "</if>"
            +"<if test='null == orderby | \"\".equals(orderby)'>"
            + "order by r.gmt_created desc"
            + "</if>"
            +"</script>")

    @Results({@Result(
            column = "product_name",
            property = "productName"
    ), @Result(
            column = "product_description",
            property = "productDescription"
    ), @Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "industry",
            property = "industry"
    ),@Result(
            column = "product_type",
            property = "productType"
    )})
    List<ProductQueryList> selectThemeticProductByCondition(@Param("productName") String productName, @Param("productDescription") String productDescription, @Param("orderby") String orderby);


    @Select("<script>"
            +"SELECT  r.product_type, r.product_description, r.product_name, r.product_id\n" +
            "FROM pdm_product_info  r , pdm_ortho_product_info i \n" +
            "WHERE  1=1 and r.product_id =i.product_id \n" +
            "<if test='null!= productName &amp; !\"\".equals(productName)'>"
            + "and r.product_name like CONCAT('%',#{productName},'%') "
            + "</if>"
            +"<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
            + "and r.product_description like CONCAT('%',#{productDescription},'%') "
            + "</if>"
            +"<if test='\"submitTimeAsc\".equals(orderby)'>"
            + "order by r.gmt_created asc"
            + "</if>"
            +"<if test='\"submitTimeDesc\".equals(orderby)'>"
            + "order by r.gmt_created desc"
            + "</if>"
            +"<if test='null == orderby | \"\".equals(orderby)'>"
            + "order by r.gmt_created desc"
            + "</if>"
            +"</script>")

    @Results({@Result(
            column = "product_name",
            property = "productName"
    ), @Result(
            column = "product_description",
            property = "productDescription"
    ), @Result(
            column = "product_id",
            property = "productId"
    ),@Result(
            column = "product_type",
            property = "productType"
    )})
    List<ProductQueryList> selectOrthoProductByCondition(@Param("productName") String productName, @Param("productDescription") String productDescription, @Param("orderby") String orderby);


    @Select("<script>"
            +"SELECT  r.product_type, r.product_description, r.product_name, r.product_id\n" +
            "FROM pdm_product_info  r , pdm_inlay_product_info i \n" +
            "WHERE  1=1 and r.product_id =i.product_id \n" +
            "<if test='null!= productName &amp; !\"\".equals(productName)'>"
            + "and r.product_name like CONCAT('%',#{productName},'%') "
            + "</if>"
            +"<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
            + "and r.product_description like CONCAT('%',#{productDescription},'%') "
            + "</if>"
            +"<if test='\"submitTimeAsc\".equals(orderby)'>"
            + "order by r.gmt_created asc"
            + "</if>"
            +"<if test='\"submitTimeDesc\".equals(orderby)'>"
            + "order by r.gmt_created desc"
            + "</if>"
            +"<if test='null == orderby | \"\".equals(orderby)'>"
            + "order by r.gmt_created desc"
            + "</if>"
            +"</script>")

    @Results({@Result(
            column = "product_name",
            property = "productName"
    ), @Result(
            column = "product_description",
            property = "productDescription"
    ), @Result(
            column = "product_id",
            property = "productId"
    ),@Result(
            column = "product_type",
            property = "productType"
    )})
    List<ProductQueryList> selectInlayProductByCondition(@Param("productName") String productName, @Param("productDescription") String productDescription, @Param("orderby") String orderby);

    @Select("<script>"
            +"SELECT  r.product_type, r.product_description, r.product_name, r.product_id, i.industry\n" +
            "FROM pdm_product_info  r , pdm_subdivision_product_info i \n" +
            "WHERE  1=1 and r.product_id =i.product_id \n" +
            "<if test='null!= productName &amp; !\"\".equals(productName)'>"
            + "and r.product_name like CONCAT('%',#{productName},'%') "
            + "</if>"
            +"<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
            + "and r.product_description like CONCAT('%',#{productDescription},'%') "
            + "</if>"
            +"<if test='\"submitTimeAsc\".equals(orderby)'>"
            + "order by r.gmt_created asc"
            + "</if>"
            +"<if test='\"submitTimeDesc\".equals(orderby)'>"
            + "order by r.gmt_created desc"
            + "</if>"
            +"<if test='null == orderby | \"\".equals(orderby)'>"
            + "order by r.gmt_created desc"
            + "</if>"
            +"</script>")

    @Results({@Result(
            column = "product_name",
            property = "productName"
    ), @Result(
            column = "product_description",
            property = "productDescription"
    ), @Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "industry",
            property = "industry"
    ),@Result(
            column = "product_type",
            property = "productType"
    )})
    List<ProductQueryList> selectSubdivisionProductByCondition(@Param("productName") String productName, @Param("productDescription") String productDescription, @Param("orderby") String orderby);

    @Select("<script>"
            +"SELECT product_id \n"+
            "FROM pdm_product_info \n"+
            "WHERE 1=1 \n"+
            "<if test='null!= client_name &amp; !\"\".equals(client_name)'>"
            + "and client_name like CONCAT('%',#{client_name},'%')"
            + "</if>"
            + "<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
            + "and product_description like CONCAT('%',#{productDescription},'%')"
            + "</if>"
            +"</script>")
    List<String> getProductIdlistByclientanddescription(@Param("client_name")String client_name,@Param("productDescription")String description);

    @Select("<script>"
            +"SELECT product_name \n"+
            "FROM pdm_product_info \n"+
            "WHERE 1=1 \n"+
            "<if test='null!=product_id  &amp; !\"\".equals(product_id)'>"
            + "and product_id=#{product_id}"
            + "</if>"
            +"</script>")

    String getProductNameById(@Param("product_id")String productId);
}
