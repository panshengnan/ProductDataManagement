//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.entity.PdmOrthoProductInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PdmOrthoProductInfoMapper {
    int insert(PdmOrthoProductInfo var1);

    List<PdmOrthoProductInfo> selectAll();

    @Select({"SELECT count(DISTINCT ortho.product_id) as count\n                \n            FROM pdm_ortho_product_info ortho  \n            WHERE ortho.gmt_created >=  '${currentDate}'"})
    int selectOrthoProductCountByDate(@Param("currentDate") String var1);

    @Select({"SELECT ortho_product_directory\nFROM pdm_ortho_product_info \nWHERE  product_id = #{productId}"})
    String selectFilePathByProductId(@Param("productId") String var1);
}
