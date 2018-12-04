package com.cgwx.dao;

import com.cgwx.data.dto.shpInfo;
import com.cgwx.data.entity.PdmAdvancedProductShpInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PdmAdvancedProductShpInfoMapper {
    int insert(PdmAdvancedProductShpInfo record);

    List<PdmAdvancedProductShpInfo> selectAll();


    @Select({"SELECT product_id,image_sourc,\n" +
            "        st_asgeojson(image_geometry) as geo,\n" +
            "        image_file,sensor,acquisitio, \n" +
            "       num_channel,chan_type,islands,\n" +
            "        cloud_cover, block_id \n"+
            "FROM   pdm_advanced_product_shp_info\n" +
            " WHERE   product_id = #{productId}"
    })
    @Results({@Result(
            column = "product_id",
            property = "productId"
    ), @Result(
            column = "image_sourc",
            property = "imageSourc"
    ), @Result(
            column = "geo",
            property = "imageGeometry"
    ), @Result(
            column = "image_file",
            property = "imageFile"
    ), @Result(
            column = "acquisitio",
            property = "acquisitio"

    ), @Result(
            column = "sensor",
            property = "sensor"
    ),@Result(
            column = "num_channel",
            property = "numChannel"
    ),@Result(
            column = "islands",
            property = "islands"
    ),@Result(
            column = "chan_type",
            property = "chanType"
    ),@Result(
            column = "cloud_cover",
            property = "cloudCover"
    ),@Result(
            column = "block_id",
            property = "blockId"
    )
    })
    List<shpInfo> getshpInfoList(@Param("productId")String productId);

}