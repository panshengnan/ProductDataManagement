//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.dto.OrthoProductList;
import com.cgwx.data.entity.PdmOrthoProductInfo;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface PdmOrthoProductInfoMapper {
    int insert(PdmOrthoProductInfo var1);

    List<PdmOrthoProductInfo> selectAll();

    @Select({"SELECT count(DISTINCT ortho.product_id) as count\n                \n            FROM pdm_ortho_product_info ortho  \n            WHERE ortho.gmt_created >=  '${currentDate}'"})
    int selectOrthoProductCountByDate(@Param("currentDate") String var1);

    @Select({"SELECT ortho_product_directory\nFROM pdm_ortho_product_info \nWHERE  product_id = #{productId}"})
    String selectFilePathByProductId(@Param("productId") String var1);
    @Select({"SELECT product_id,ortho_product_name,\n" +
            "        st_asgeojson(image_geo) as geo,ortho_product_directory, resolution, image_breath,\n" +
            "        producer,satellite,sensor,capture_time,size_of_tif, client_name, deliever_name, \n" +
            "        deliever_time,geographic_info\n" +
            " FROM   pdm_ortho_product_info\n" +
            " WHERE   product_id = #{productId}"
    })
    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "ortho_product_name",
            property = "orthoProductName"
    ), @Result(
            column = "producer",
            property = "producer"
    ), @Result(
            column = "satellite",
            property = "satellite"
    ), @Result(
            column = "geo",
            property = "imageGeo"

    ), @Result(
            column = "sensor",
            property = "sensor"
    ), @Result(
            column = "resolution",
            property = "resolution"
    ), @Result(
            column = "image_breath",
            property = "imageBreath"
    ),  @Result(
            column = "capture_time",
            property = "captureTime"
    ), @Result(
            column = "size_of_tif",
            property = "sizeOfTif"
    ), @Result(
            column = "client_name",
            property = "clientName"
    ), @Result(
            column = "deliever_name",
            property = "delieverName"
    ), @Result(
            column = "deliever_time",
            property = "delieverTime"
    ),@Result(
            column = "geographic_info",
            property = "geographicInfo"
    ),@Result(
            column = "ortho_product_directory",
            property = "orthoProductDirectory"
    )
    })
        //查询请求详细信息调用的数据库语句
    PdmOrthoProductInfo selectOrthoProductDetailByProductId(@Param("productId") String productId);






    @Select("<script>"
            +"SELECT product_id, ortho_product_name, geographic_info,  client_name,deliever_name,\n" +
            "st_asgeojson(image_geo) as geo,producer,satellite,sensor,resolution,image_breath,size_of_tif,capture_time,\n" +
            "deliever_time\n" +
            "FROM pdm_ortho_product_info\n" +
            "WHERE  1=1  \n" +
            "<if test='null!= productName &amp; !\"\".equals(productName)'>"
            + "and ortho_product_name like CONCAT('%',#{productName},'%') "
            + "</if>"
            +"<if test='null!= clientName &amp; !\"\".equals(clientName)'>"
            + "and client_name like CONCAT('%',#{clientName},'%') "
            + "</if>"
            +"<if test='null!= delieverName &amp; !\"\".equals(delieverName)'>"
            + "and deliever_name like CONCAT('%',#{delieverName},'%') "
            + "</if>"
            +"<if test='null!= imageBreath &amp; !\"\".equals(imageBreath)'>"
            + "and image_breath like CONCAT('%',#{imageBreath},'%')  "
            + "</if>"
            +"<if test='null!= satellite &amp; !\"\".equals(satellite)'>"
            + "and satellite =#{satellite} "
            + "</if>"
            +"<if test='null!= sensor &amp; !\"\".equals(sensor)'>"
            + "and sensor =#{sensor} "
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
            column = "ortho_product_name",
            property = "orthoProductName"
    ), @Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "geographic_info",
            property = "geographicInfo"
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
            column = "resolution",
            property = "resolution"
    ), @Result(
            column = "image_breath",
            property = "imageBreath"
    ), @Result(
            column = "capture_time",
            property = "captureTime"
    ), @Result(
            column = "deliever_time",
            property = "delieverTime"
    )})
    List<OrthoProductList> selectOrthoProductByCondition (@Param("productName") String productName, @Param("orderby") String orderby,
                                                          @Param("satellite") String satellite,
                                                          @Param("sensor") String sensor, @Param("clientName") String clientName,
                                                          @Param("delieverName") String delieverName,
                                                          @Param("resolution") BigDecimal resolution, @Param("imageBreath") String imageBreath);
}
