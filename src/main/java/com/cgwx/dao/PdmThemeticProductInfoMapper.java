//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.entity.PdmThemeticProductInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PdmThemeticProductInfoMapper {
    int insert(PdmThemeticProductInfo var1);

    List<PdmThemeticProductInfo> selectAll();

    @Select({"SELECT count(DISTINCT theme.product_id) as count\n                \n            FROM pdm_themetic_product_info theme  \n            WHERE theme.gmt_created >=  '${currentDate}'"})
    int selectThemeticProductCountByDate(@Param("currentDate") String var1);

    @Select({"SELECT parent_directory\nFROM pdm_themetic_product_info \nWHERE  product_id = #{productId}"})
    String selectFilePathByProductId(@Param("productId") String var1);
}
