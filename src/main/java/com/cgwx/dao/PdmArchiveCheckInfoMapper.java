
package com.cgwx.dao;

import com.cgwx.data.entity.PdmArchiveCheckInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PdmArchiveCheckInfoMapper {
    int insert(PdmArchiveCheckInfo var1);

    List<PdmArchiveCheckInfo> selectAll();

    @Select({"SELECT file_name\nFROM pdm_archive_check_info\nWHERE product_id = #{tempId} "})
    String selectFileNameById(@Param("tempId") String var1);

    @Select({"SELECT temporary_path\nFROM pdm_archive_check_info\nWHERE product_id = #{tempId} "})
    String selectTemporaryPathById(@Param("tempId") String var1);
}
