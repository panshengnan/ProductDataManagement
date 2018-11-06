//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.dto.ThemeticProductList;
import com.cgwx.data.entity.PdmThemeticProductInfo;
import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface PdmThemeticProductInfoMapper {
    int insert(PdmThemeticProductInfo var1);

    List<PdmThemeticProductInfo> selectAll();

    @Select({"SELECT count(DISTINCT theme.product_id) as count\n                \n            FROM pdm_themetic_product_info theme  \n            WHERE theme.gmt_created >=  '${currentDate}'"})
    int selectThemeticProductCountByDate(@Param("currentDate") String var1);

    @Select({"SELECT parent_directory\nFROM pdm_themetic_product_info \nWHERE  product_id = #{productId}"})
    String selectFilePathByProductId(@Param("productId") String var1);

    @Select({"SELECT product_id, themetic_product_name, industry, parent_directory, is_multi_period, \n" +
            "          client_name, deliever_name,deliever_time,gmt_created,gmt_modified\n" +
            "            FROM pdm_themetic_product_info\n" +
            "            WHERE product_id = #{productId}"
    })
    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "themetic_product_name",
            property = "themeticProductName"
    ), @Result(
            column = "industry",
            property = "industry"
    ), @Result(
            column = "parent_directory",
            property = "parentDirectory"
    ), @Result(
            column = "is_multi_period",
            property = "isMultiPeriod"
    ), @Result(
            column = "client_name",
            property = "clientName"
    ), @Result(
            column = "deliever_name",
            property = "delieverName"
    ), @Result(
            column = "deliever_time",
            property = "delieverTime"
    )
    })
        //查询请求详细信息调用的数据库语句
    PdmThemeticProductInfo selectThemeticProductDetailPart1ByProductId(@Param("productId") String productId);

    @Select({"SELECT industry\n" +
            "            FROM pdm_themetic_product_info\n" +
            "            WHERE product_id = #{productId}"
    })

        //查询请求详细信息调用的数据库语句
    String selectIndustryByProductId(@Param("productId") String productId);




    @Select("<script>"
            +"SELECT  r.product_id, r.themetic_product_name, r.industry,  r.client_name,r.deliever_name,\n" +
            "st_asgeojson(i.image_geo) as geo,i.producer,i.satellite,i.sensor,i.size_of_tif,i.imaging_time,\n" +
            "i.produce_time,i.single_period_product_id,i.single_period_product_name\n" +
            "FROM pdm_themetic_product_info  r , pdm_themetic_product_detail_info i \n" +
            "WHERE  1=1 and r.product_id =i.product_id \n" +
            "<if test='null!= productName &amp; !\"\".equals(productName)'>"
            + "and r.themetic_product_name like CONCAT('%',#{productName},'%') "
            + "</if>"
            +"<if test='null!= clientName &amp; !\"\".equals(clientName)'>"
            + "and r.client_name like CONCAT('%',#{clientName},'%') "
            + "</if>"
            +"<if test='null!= delieverName &amp; !\"\".equals(delieverName)'>"
            + "and r.deliever_name like CONCAT('%',#{delieverName},'%') "
            + "</if>"
            +"<if test='null!= industry &amp; !\"\".equals(industry)'>"
            + "and r.industry l=#{industry} "
            + "</if>"
            +"<if test='null!= satellite &amp; !\"\".equals(satellite)'>"
            + "and i.satellite =#{satellite} "
            + "</if>"
            +"<if test='null!= sensor &amp; !\"\".equals(sensor)'>"
            + "and i.sensor =#{sensor} "
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
            column = "themetic_product_name",
            property = "themetic_productName"
    ), @Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "industry",
            property = "industry"
    ), @Result(
            column = "client_name",
            property = "clientName"
    ), @Result(
            column = "deliever_name",
            property = "delieverName"
    ), @Result(
            column = "geo",
            property = "imageGeo"
    ), @Result(
            column = "producer",
            property = "producer"
    ), @Result(
            column = "satellite",
            property = "satellite"
    ), @Result(
            column = "sensor",
            property = "sensor"
    ), @Result(
            column = "size_of_tif",
            property = "sizeOfTif"
    ), @Result(
            column = "imaging_time",
            property = "imagingTime"
    ), @Result(
            column = "single_period_product_id",
            property = "singlePeriodProductId"
    ), @Result(
            column = "single_period_product_directory",
            property = "singlePeriodProductDirectory"
    ), @Result(
            column = "produce_time",
            property = "produceTime"
    )})
    List<ThemeticProductList> selectThemeticProductByCondition(@Param("productName") String productName, @Param("orderby") String orderby,
                                                               @Param("satellite") String satellite, @Param("industry") String industry, @Param("sensor") String sensor, @Param("clientName") String clientName,
                                                               @Param("delieverName") String delieverName);


}
