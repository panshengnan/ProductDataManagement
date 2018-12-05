package com.cgwx.service.impl;

import com.cgwx.dao.PdmUserInfoMapper;

import com.cgwx.data.entity.PdmUserInfo;

import com.cgwx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.net.ServerSocket;
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
        System.out.println("密码是："+result);
        String id = "false";
        System.out.println("输入的密码是："+passwd);
        if (passwd.equals(result))
            id = pdmUserInfoMapper.selectIdByAccount(account);
        System.out.println("id是："+id);
        return  id;
    }

    @Override
    public String modifyPasswdByAccount(String userId,String oldPasswd,String newPasswd)
    {
        String account = pdmUserInfoMapper.selectUserNameByUserId(userId);
        System.out.println("用户是："+account);
        if (account == null || account.equals(""))
            return "false";
        else {
            oldPasswd = DigestUtils.md5DigestAsHex(oldPasswd.getBytes());
            if(loginByPasswd(account,oldPasswd).equals(userId)) {
                System.out.println("登陆成功！");
                pdmUserInfoMapper.insertPasswd(account, DigestUtils.md5DigestAsHex(newPasswd.getBytes()));
                return "true";
            }
            else
                return "false";
        }
    }

    @Override
    public List<String> getUserList()
    {
        return pdmUserInfoMapper.selectAllUsers();
    }

}
