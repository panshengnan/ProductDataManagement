package com.cgwx.dao;

import com.cgwx.data.entity.PdmUserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PdmUserInfoMapper {
    int insert(PdmUserInfo record);

    List<PdmUserInfo> selectAll();



    @Select("SELECT password\n" +
            "FROM pdm_user_info\n" +
            "WHERE upper(user_name) = #{account}"
    )
    String selectPasswdByAccount(@Param("account") String account);



    @Select("SELECT user_id\n" +
            "FROM pdm_user_info\n" +
            "WHERE user_name = #{account}"
    )
    String selectIdByAccount(@Param("account") String account);




    @Select("SELECT count(user_id)\n" +
            "FROM pdm_user_info\n" +
            "WHERE upper(user_name) = #{account}"
    )
    int selectCountIdByAccount(@Param("account") String account);

    @Select("update pdm_user_info set password = #{passwd}\n" +
            "WHERE upper(user_name) = #{account}"
    )
    void insertPasswd(@Param("account") String account,@Param("passwd") String passwd);

    @Select("SELECT user_name\n" +
            "FROM pdm_user_info\n" +
            "WHERE user_id = #{userId}"
    )
    String selectUserNameByUserId(@Param("userId") String userId);

    @Select("SELECT user_name\n" +
            "FROM pdm_user_info\n" +
            "WHERE 1=1 order by user_name collate \"C\" "
    )
    List<String> selectAllUsers();
}