package com.cgwx.dao;

import com.cgwx.data.dto.ThemeticProductSimpleInfo;
import com.cgwx.data.entity.PdmThemeticProductDetailInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PdmThemeticProductDetailInfoMapper {
    int insert(PdmThemeticProductDetailInfo record);

    List<PdmThemeticProductDetailInfo> selectAll();
    @Select({"SELECT single_period_product_id\n" +
            " FROM pdm_themetic_product_detail_info\n" +
            "WHERE product_id = #{productId}"
    })
    String selectSinglePeriodProductIdByProductId(@Param("productId") String productId);



//    @Select({"SELECT product_id, single_period_product_id, single_period_product_directory, producer, \n" +
//            "              satellite, st_asgeojson(image_geo) as geo, sensor, resolution, image_breath,\n" +
//            "               cloud_percent, center_imaging_time, size_of_tif, client_name, deliever_name, \n" +
//            "                deliever_time, nation, province, city, county,single_period_product_name, \n" +
//            "             village, geographic_info, gmt_created,gmt_modified\n" +
//            "            FROM pdm_themetic_product_detail_info\n" +
//            "            WHERE single_period_product_id = #{singlePeriodProductId}"
//    })
//    @Results({@Result(
//            column = "product_id",
//            property = "productId"
//    ), @Result(
//            column = "single_period_product_id",
//            property = "singlePeriodProductId"
//    ), @Result(
//            column = "single_period_product_directory",
//            property = "singlePeriodProductDirectory"
//    ), @Result(
//            column = "producer",
//            property = "producer"
//    ), @Result(
//            column = "satellite",
//            property = "satellite"
//    ), @Result(
//            column = "geo",
//            property = "imageGeo"
//
//    ), @Result(
//            column = "sensor",
//            property = "sensor"
//    ), @Result(
//            column = "resolution",
//            property = "resolution"
//    ), @Result(
//            column = "image_breath",
//            property = "imageBreath"
//    ), @Result(
//
//            column = "cloud_percent",
//            property = "cloudPercent"
//    ),  @Result(
//            column = "center_imaging_time",
//            property = "centerImagingTime"
//    ), @Result(
//            column = "size_of_tif",
//            property = "sizeOfTif"
//    ), @Result(
//            column = "client_name",
//            property = "clientName"
//    ), @Result(
//            column = "deliever_name",
//            property = "delieverName"
//    ), @Result(
//            column = "deliever_time",
//            property = "delieverTime"
//    ), @Result(
//            column = "nation",
//            property = "nation"
//    ), @Result(
//            column = "province",
//            property = "province"
//    ), @Result(
//            column = "city",
//            property = "city"
//    ), @Result(
//            column = "county",
//            property = "county"
//    ), @Result(
//            column = "village ",
//            property = "village"
//    ), @Result(
//            column = "single_period_product_name",
//            property = "singlePeriodProductName"
//    ), @Result(
//            column = "geographic_info",
//            property = "geographicInfo"
//    )
//    })
//        //查询请求详细信息调用的数据库语句
//    PdmThemeticProductDetailInfo selectThemeticProductDetailByProductIdPart2(@Param("singlePeriodProductId") String singlePeriodProductId);


    @Select({"SELECT product_id, single_period_product_id, single_period_product_directory, producer, \n" +
            "              satellite, st_asgeojson(image_geo) as geo, sensor, \n" +
            "               imaging_time, size_of_tif,  \n" +
            "                single_period_product_name,produce_time, \n" +
            "               gmt_created,gmt_modified\n" +
            "            FROM pdm_themetic_product_detail_info\n" +
            "            WHERE single_period_product_id = #{singlePeriodProductId}and product_id= #{productId}"
    })
    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "single_period_product_id",
            property = "singlePeriodProductId"
    ), @Result(
            column = "single_period_product_directory",
            property = "singlePeriodProductDirectory"
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
            column = "size_of_tif",
            property = "sizeOfTif"
    ),@Result(
            column = "single_period_product_name",
            property = "singlePeriodProductName"
    ),@Result(
            column = "produce_time",
            property = "produceTime"
    ),@Result(
            column = "imaging_time",
            property = "imagingTime"
    )
    })
        //查询请求详细信息调用的数据库语句
    PdmThemeticProductDetailInfo selectThemeticProductDetailByProductIdPart2(@Param("singlePeriodProductId") String singlePeriodProductId,
                                                                               @Param("productId") String productId );




    @Select({"SELECT COUNT(single_period_product_id)\n" +
            "FROM pdm_themetic_product_detail_info\n" +
            "WHERE product_id = #{productId}"
    })


        //查询多期专题展品期数
    Integer countSinglePeriodThemeticProductId(@Param("productId") String productId);

    @Select({"SELECT single_period_product_id\n" +
            "FROM pdm_themetic_product_detail_info\n" +
            "WHERE product_id = #{productId}"
    })


        //查询多期专题展品中每期的id
    List<String> selecSinglePeriodThemeticProductList(@Param("productId") String productId);

    @Select("<script>"
            +"SELECT product_id,st_asgeojson(image_geo) as geo,single_period_product_id\n" +
            "            FROM pdm_themetic_product_detail_info\n"+
            "            WHERE 1=1 \n"+
            "<if test='null!= producer &amp; !\"\".equals(producer)'>"
            + "and producer like CONCAT('%',#{producer},'%') "
            +"</if>"
            +"<if test='null!=image_geo '>"
            +"and  st_disjoint(st_geomfromgeojson(st_asgeojson(image_geo)),st_geomfromgeojson(#{image_geo}))=false"
            +"</if>"
            +"</script>")
    @Results({@Result(
            column = "geo",
            property = "imageGeo"

    ), @Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "single_period_product_id",
            property = "singlePeriodId")
    })
    List<ThemeticProductSimpleInfo> selectSimpleinfoByProducerandGeo(@Param("producer")String producer,
                                                                     @Param("image_geo")Object image_geo);
    @Select({"SELECT product_id,st_asgeojson(image_geo) as geo,single_period_product_id\n" +
            "            FROM pdm_themetic_product_detail_info\n"
    })
    @Results({@Result(
            column = "geo",
            property = "imageGeo"

    ), @Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "single_period_product_id",
            property = "singlePeriodId")
    })
    List<ThemeticProductSimpleInfo> selectSimpleinfotest();


    @Select({"SELECT product_id,st_asgeojson(image_geo) as geo,single_period_product_id\n" +
            "            FROM pdm_themetic_product_detail_info\n"+
            "            WHERE product_id=#{productId}"
    })
    @Results({@Result(
            column = "geo",
            property = "imageGeo"

    ), @Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "single_period_product_id",
            property = "singlePeriodId")
    })
    List<ThemeticProductSimpleInfo> selectSimpleinfoById(@Param("productId")String productId);
}