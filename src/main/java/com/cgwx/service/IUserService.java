package com.cgwx.service;


import com.cgwx.data.entity.PdmUserInfo;

public interface IUserService {


    String register(PdmUserInfo pdmUserInfo);
    String loginByPasswd(String account, String passwd);
    String modifyPasswdByAccount(String account, String passwd);
}
