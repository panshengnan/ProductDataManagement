package com.cgwx.service.impl;

import com.cgwx.dao.PdmUserInfoMapper;

import com.cgwx.data.entity.PdmUserInfo;

import com.cgwx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    public PdmUserInfoMapper pdmUserInfoMapper;


    public String register (PdmUserInfo pdmUserInfo){


        int count = pdmUserInfoMapper.selectCountIdByAccount(pdmUserInfo.getUserName());

        if (count != 0)
            return "false";
        else {

            pdmUserInfoMapper.insert(pdmUserInfo);

            return "true";
        }


    }

    @Override
    public String loginByPasswd(String account,String passwd){

        String result = pdmUserInfoMapper.selectPasswdByAccount(account);
        String id = "false";
        if (passwd.equals(result))
            id = pdmUserInfoMapper.selectIdByAccount(account);
        return  id;
    }

    @Override
    public String modifyPasswdByAccount(String account,String passwd)
    {
        int count = pdmUserInfoMapper.selectCountIdByAccount(account);
        if (count == 0)
            return "false";
        else {
            pdmUserInfoMapper.insertPasswd(account, DigestUtils.md5DigestAsHex(passwd.getBytes()));
            return "true";
        }
    }

    @Override
    public List<String> getUserList()
    {
        return pdmUserInfoMapper.selectAllUsers();
    }

}
