//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.dto.AdvanceProductSimpleInfo;
import com.cgwx.data.dto.SubdivisionProductList;
import com.cgwx.data.dto.ThemeticProductSimpleInfo;
import com.cgwx.data.entity.PdmSubdivisionProductInfo;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface PdmSubdivisionProductInfoMapper {
    int insert(PdmSubdivisionProductInfo var1);

    List<PdmSubdivisionProductInfo> selectAll();

    @Select({"SELECT count(DISTINCT subdivision.product_id) as count\n                \n            FROM pdm_subdivision_product_info subdivision  \n            WHERE subdivision.gmt_created >=  '${currentDate}'"})
    int selectSubdivisionProductCountByDate(@Param("currentDate") String var1);

    @Select({"SELECT subdivision_product_directory\nFROM pdm_subdivision_product_info \nWHERE  product_id = #{productId}"})
    String selectFilePathByProductId(@Param("productId") String var1);
    @Select({"SELECT product_id, subdivision_product_name, producer, \n" +
            "               st_asgeojson(image_geo) as geo, resolution, \n" +
            "               number_of_tif,industry,subdivision_product_directory,  \n" +
            "                geographic_info,client_name,deliever_name,deliever_time, \n" +
            "               gmt_created,gmt_modified\n" +
            "            FROM pdm_subdivision_product_info\n" +
            "            WHERE product_id = #{productId}"
    })
    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "subdivision_product_name",
            property = "subdivisionProductName"
    ), @Result(
            column = "industry",
            property = "industry"
    ), @Result(
            column = "producer",
            property = "producer"
    ), @Result(
            column = "subdivision_product_directory",
            property = "subdivisionProductDirectory"
    ), @Result(
            column = "geo",
            property = "imageGeo"

    ), @Result(
            column = "resolution",
            property = "resolution"
    ),@Result(
            column = "number_of_tif",
            property = "numberOfTif"
    ),@Result(
            column = "geographic_info",
            property = "geographicInfo"
    ),@Result(
            column = "client_name",
            property = "clientName"
    ),@Result(
            column = "deliever_name",
            property = "delieverName"
    ),@Result(
            column = "deliever_time",
            property = "delieverTime"
    )
    })
        //查询请求详细信息调用的数据库语句
    PdmSubdivisionProductInfo selectSubdivisionProductDetailByProductId(@Param("productId") String productId);

    @Select("<script>"
            +"SELECT product_id, subdivision_product_name, producer, \n" +
            "               st_asgeojson(image_geo) as geo, resolution, \n" +
            "               number_of_tif,industry,subdivision_product_directory,  \n" +
            "                geographic_info,client_name,deliever_name,deliever_time \n" +
            "FROM pdm_subdivision_product_info\n" +
            "WHERE  1=1  \n" +
            "<if test='null!= productName &amp; !\"\".equals(productName)'>"
            + "and subdivision_product_name like CONCAT('%',#{productName},'%') "
            + "</if>"
            +"<if test='null!= clientName &amp; !\"\".equals(clientName)'>"
            + "and client_name like CONCAT('%',#{clientName},'%') "
            + "</if>"
            +"<if test='null!= delieverName &amp; !\"\".equals(delieverName)'>"
            + "and deliever_name like CONCAT('%',#{delieverName},'%') "
            + "</if>"
            +"<if test='null!= industry &amp; !\"\".equals(industry)'>"
            + "and satellite =#{industry} "
            + "</if>"
            +"<if test='null!= resolution &amp; !\"\".equals(resolution)'>"
            + "and resolution =#{resolution} "
            + "</if>"
            +"<if test='\"submitTimeAsc\".equals(orderby)'>"
            + "order by gmt_created asc"
            + "</if>"
            +"<if test='\"submitTimeDesc\".equals(orderby)'>"
            + "order by gmt_created desc"
            + "</if>"
            +"<if test='null == orderby | \"\".equals(orderby)'>"
            + "order by gmt_created desc"
            + "</if>"
            +"</script>")

    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "subdivision_product_name",
            property = "subdivisionProductName"
    ), @Result(
            column = "industry",
            property = "industry"
    ), @Result(
            column = "producer",
            property = "producer"
    ), @Result(
            column = "geo",
            property = "imageGeo"

    ), @Result(
            column = "resolution",
            property = "resolution"
    ),@Result(
            column = "number_of_tif",
            property = "numberOfTif"
    ),@Result(
            column = "geographic_info",
            property = "geographicInfo"
    ),@Result(
            column = "client_name",
            property = "clientName"
    ),@Result(
            column = "deliever_name",
            property = "delieverName"
    ),@Result(
            column = "deliever_time",
            property = "delieverTime"

    )})
    List<SubdivisionProductList> selectSubdivisionProductByCondition (@Param("productName") String productName, @Param("orderby") String orderby,
                                                                      @Param("clientName") String clientName,
                                                                      @Param("delieverName") String delieverName,
                                                                      @Param("resolution") BigDecimal resolution, @Param("industry") String industry);

    @Select({"SELECT product_id,st_asgeojson(image_geo) as geo\n" +
            "            FROM pdm_subdivision_product_info\n"
    })
    @Results({@Result(
            column = "geo",
            property = "imageGeo"

    ), @Result(
            column = "product_id",
            property = "productId")
    })
    List<ThemeticProductSimpleInfo> selectSimpleinfo();



    @Select("<script>"
            +"SELECT product_id,st_asgeojson(image_geo) as geo, subdivision_product_name\n" +
            "            FROM pdm_subdivision_product_info  \n"+
            "            WHERE 1=1 \n"+
            "<if test='null!= producer &amp; !\"\".equals(producer)'>"
            + "and producer like CONCAT('%',#{producer},'%') "
            +"</if>"
            +"<if test='null!=image_geo '>"
            +"and  st_disjoint(st_geomfromgeojson(st_asgeojson(image_geo)),st_geomfromgeojson(#{image_geo}))=false"
            +"</if>"
            +"<if test='null!= product_name &amp; !\"\".equals(product_name)'>"
            + "and subdivision_product_name like CONCAT('%',#{product_name},'%') "
            +"</if>"
            +"and product_id IN ("
            + "SELECT product_id \n" +
            "            FROM pdm_product_info \n" +
            "            WHERE 1=1 \n" +
            "<if test='null!= deliver_name &amp; !\"\".equals(deliver_name)'>"
            + "and deliver_name = #{deliver_name}"
            + "</if>"
            + "<if test='null!= produce_area &amp; !\"\".equals(produce_area)'>"
            + "and produce_area = #{produce_area}"
            + "</if>"
            + "<if test='null!= deliver_method &amp; !\"\".equals(deliver_method)'>"
            + "and deliver_method = #{deliver_method}"
            + "</if>"
            + "<if test='null!= produceStartTime'>"
            + "and produce_time  &gt;= #{produceStartTime}"
            + "</if>"
            + "<if test='null!= produceEndTime'>"
            + "and produce_time  &lt;= #{produceEndTime}"
            + "</if>"
            + "<if test='null!= deliverStartTime'>"
            + "and deliver_time  &gt;= #{deliverStartTime}"
            + "</if>"
            + "<if test='null!= deliverEndTime'>"
            + "and deliver_time  &lt;= #{deliverEndTime}"
            + "</if>"
            +")"
            +"</script>")
    @Results({@Result(
            column = "geo",
            property = "imageGeo"

    ), @Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "subdivision_product_name",
            property = "productName"
    )})
    List<AdvanceProductSimpleInfo> selectSimpleinfoByconditions(@Param("producer")String producer,
                                                                @Param("image_geo")Object image_geo,
                                                                @Param("deliver_name")String deliverName,
                                                                @Param("produce_area")String produceArea,
                                                                @Param("deliver_method")String deliverMethod,
                                                                @Param("produceStartTime") Date produceStartTime,
                                                                @Param("produceEndTime") Date produceEndTime,
                                                                @Param("deliverStartTime") Date deliverStartTime,
                                                                @Param("deliverEndTime") Date deliverEndTime,
                                                                @Param("product_name")String productName);
}
