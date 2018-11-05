//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.entity.PdmSubdivisionProductInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PdmSubdivisionProductInfoMapper {
    int insert(PdmSubdivisionProductInfo var1);

    List<PdmSubdivisionProductInfo> selectAll();

    @Select({"SELECT count(DISTINCT subdivision.product_id) as count\n                \n            FROM pdm_subdivision_product_info subdivision  \n            WHERE subdivision.gmt_created >=  '${currentDate}'"})
    int selectSubdivisionProductCountByDate(@Param("currentDate") String var1);

    @Select({"SELECT subdivision_product_directory\nFROM pdm_subdivision_product_info \nWHERE  product_id = #{productId}"})
    String selectFilePathByProductId(@Param("productId") String var1);
}
