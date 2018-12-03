package com.cgwx.dao;

import com.cgwx.data.dto.AdvanceProductSimpleInfo;
import com.cgwx.data.dto.OrthoProductDetail;
import com.cgwx.data.dto.OrthoProductList;
import com.cgwx.data.dto.ThemeticProductSimpleInfo;
import com.cgwx.data.entity.PdmOrthoProductInfo;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Mapper
public interface PdmOrthoProductInfoMapper {
    int insert(PdmOrthoProductInfo record);

    List<PdmOrthoProductInfo> selectAll();


    @Select({"SELECT count(DISTINCT ortho.product_id) as count\n                \n            FROM pdm_ortho_product_info ortho  \n            WHERE ortho.gmt_created >=  '${currentDate}'"})
    int selectOrthoProductCountByDate(@Param("currentDate") String var1);

    @Select({"SELECT ortho_product_directory\nFROM pdm_ortho_product_info \nWHERE  product_id = #{productId}"})
    String selectFilePathByProductId(@Param("productId") String var1);
    @Select({"SELECT product_id,ortho_product_name,\n" +
            "        st_asgeojson(image_geo) as geo,\n" +
            "        satellite,sensor,center_time, \n" +
            "       geographic_info,producer,\n" +
            "        receive_station, receive_time, swing_satellite_angle,cloud_percent,width_in_meters,height_in_meters,\n" +
            "        product_quality,bands,center_longitude,center_latitude \n"+
            "FROM   pdm_ortho_product_info\n" +
            " WHERE   product_id = #{productId}"
    })
    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "ortho_product_name",
            property = "productName"
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
    ),@Result(
            column = "geographic_info",
            property = "geographicInfo"
    ),@Result(
            column = "receive_station",
            property = "receiveStation"
    ),@Result(
            column = "receive_time",
            property = "receiveTime"
    ),@Result(
            column = "swing_satellite_angle",
            property = "swingSatelliteAngle"
    ),@Result(
            column = "cloud_percent",
            property = "cloudPercent"
    ),@Result(
            column = "center_time",
            property = "centerTime"
    ),@Result(
            column = "width_in_meters",
            property = "widthInMeters"
    ),@Result(
            column = "height_in_meters",
            property = "heightInMeters"
    ),@Result(
            column = "product_quality",
            property = "productQuality"
    ),@Result(
            column = "bands",
            property = "bands"
    ),@Result(
            column = "center_longitude",
            property = "centerLongitude"
    ),@Result(
            column = "center_latitude",
            property = "centerLatitude"
    )
    })
        //查询请求详细信息调用的数据库语句
    OrthoProductDetail selectOrthoProductDetailByProductId(@Param("productId") String productId);






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

    @Select({"SELECT product_id,st_asgeojson(image_geo) as geo\n" +
            "            FROM pdm_ortho_product_info\n"
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
            +"SELECT product_id,st_asgeojson(image_geo) as geo, ortho_product_name\n" +
            "            FROM pdm_ortho_product_info  \n"+
            "            WHERE 1=1 \n"+
            "<if test='null!= producer &amp; !\"\".equals(producer)'>"
            + "and producer like CONCAT('%',#{producer},'%') "
            +"</if>"
            +"<if test='null!=image_geo '>"
            +"and  st_disjoint(st_geomfromgeojson(st_asgeojson(image_geo)),st_geomfromgeojson(#{image_geo}))=false"
            +"</if>"
            +"<if test='null!= product_name &amp; !\"\".equals(product_name)'>"
            + "and ortho_product_name like CONCAT('%',#{product_name},'%') "
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
            column = "ortho_product_name",
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





    @Select("<script>"
            +"<if test='isOrtho '>"
            +"SELECT product_id,st_asgeojson(image_geo) as geo, ortho_product_name\n" +
            "            FROM pdm_ortho_product_info  \n"+
            "            WHERE 1=1 \n"+
            "<if test='null!= producer &amp; !\"\".equals(producer)'>"
            + "and producer like CONCAT('%',#{producer},'%') "
            +"</if>"
            +"<if test='null!=imagegeo '>"
            +"and  st_disjoint(st_geomfromgeojson(st_asgeojson(image_geo)),st_geomfromgeojson(#{imagegeo}))=false"
            +"</if>"
            +"<if test='null!= product_name &amp; !\"\".equals(product_name)'>"
            + "and ortho_product_name like CONCAT('%',#{product_name},'%') "
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
            +"</if>"
            +"<if test='isInlay '>"
            +"<if test='isOrtho '>"
            +"UNION"
            +"</if>"
            +" SELECT product_id,st_asgeojson(image_geo) as geo, inlay_product_name as ortho_product_name\n" +
            "            FROM pdm_inlay_product_info  \n"+
            "            WHERE 1=1 \n"+
            "<if test='null!= producer &amp; !\"\".equals(producer)'>"
            + "and producer like CONCAT('%',#{producer},'%') "
            +"</if>"
            +"<if test='null!=imagegeo '>"
            +"and  st_disjoint(st_geomfromgeojson(st_asgeojson(image_geo)),st_geomfromgeojson(#{imagegeo}))=false"
            +"</if>"
            +"<if test='null!= product_name &amp; !\"\".equals(product_name)'>"
            + "and inlay_product_name like CONCAT('%',#{product_name},'%') "
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
            + "and produce_time &gt;= #{produceStartTime}"
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
            +"</if>"
            +"<if test='isSubdivision '>"
            +"<if test='isOrtho or isInlay'>"
            +"UNION"
            +"</if>"
            +" SELECT product_id,st_asgeojson(image_geo) as geo, subdivision_product_name as ortho_product_name\n" +
            "            FROM pdm_subdivision_product_info  \n"+
            "            WHERE 1=1 \n"+
            "<if test='null!= producer &amp; !\"\".equals(producer)'>"
            + "and producer like CONCAT('%',#{producer},'%') "
            +"</if>"
            +"<if test='null!=imagegeo '>"
            +"and  st_disjoint(st_geomfromgeojson(st_asgeojson(image_geo)),st_geomfromgeojson(#{imagegeo}))=false"
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
            +"</if>"
            +"</script>")
    @Results({@Result(
            column = "geo",
            property = "imageGeo"

    ), @Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "ortho_product_name",
            property = "productName"
    )})
    List<AdvanceProductSimpleInfo> selectSimpleinfoByAllconditions(@Param("producer")String producer,
                                                                   @Param("imagegeo")Object image_geo,
                                                                   @Param("deliver_name")String deliverName,
                                                                   @Param("produce_area")String produceArea,
                                                                   @Param("deliver_method")String deliverMethod,
                                                                   @Param("produceStartTime") Date produceStartTime,
                                                                   @Param("produceEndTime") Date produceEndTime,
                                                                   @Param("deliverStartTime") Date deliverStartTime,
                                                                   @Param("deliverEndTime") Date deliverEndTime,
                                                                   @Param("product_name")String productName,
                                                                   @Param("isOrtho") boolean isOrtho,
                                                                   @Param("isInlay") boolean isInlay,
                                                                   @Param("isSubdivision") boolean isSubdivision);
}