//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cgwx.dao;

import com.cgwx.data.entity.PdmProductInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PdmProductInfoMapper {
    int insert(PdmProductInfo var1);

    List<PdmProductInfo> selectAll();

    @Select({"SELECT client_name\nFROM pdm_product_info\nWHERE client_name like  '%${clientName}%' order by client_name collate \"C\" "})
    List<String> selectClientNameList(@Param("clientName") String var1);

    @Select({"SELECT deliver_name\nFROM pdm_product_info\nWHERE deliver_name like  '%${deliverName}%' order by deliver_name collate \"C\" "})
    List<String> selectDeliverNameList(@Param("deliverName") String var1);

    @Select({"SELECT producer\nFROM pdm_product_info\nWHERE producer like  '%${producer}%' order by producer collate \"C\" "})
    List<String> selectProducerList(@Param("producer") String var1);
}
