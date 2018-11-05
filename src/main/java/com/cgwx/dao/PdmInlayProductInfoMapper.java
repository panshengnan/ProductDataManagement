//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.entity.PdmInlayProductInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PdmInlayProductInfoMapper {
    int insert(PdmInlayProductInfo var1);

    List<PdmInlayProductInfo> selectAll();

    @Select({"SELECT count(DISTINCT inlay.product_id) as count\n                \n            FROM pdm_inlay_product_info inlay  \n            WHERE inlay.gmt_created >=  '${currentDate}'"})
    int selectInlayProductCountByDate(@Param("currentDate") String var1);

    @Select({"SELECT inlay_product_directory\nFROM pdm_inlay_product_info \nWHERE  product_id = #{productId}"})
    String selectFilePathByProductId(@Param("productId") String var1);
}
