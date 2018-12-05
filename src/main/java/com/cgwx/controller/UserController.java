package com.cgwx.controller;

import com.cgwx.aop.result.Result;
import com.cgwx.aop.result.ResultUtil;

import com.cgwx.data.entity.PdmUserInfo;
import com.cgwx.service.IProductArchiveService;
import com.cgwx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    IProductArchiveService iProductArchiveService;

    @RequestMapping("/userRegister")
    @CrossOrigin(methods = RequestMethod.POST)
    @ResponseBody
    public Result userRegister(@RequestParam(value = "departmentName", required = true) String departmentName,
                               @RequestParam(value = "phoneNumber", required = true) String phoneNumber,
                               @RequestParam(value = "password", required = true) String password,
                               @RequestParam(value = "userName", required = true) String userName)
//                               @RequestParam(value = "role", required = true) int role)
    {

        PdmUserInfo pdmUserInfo = new PdmUserInfo();
        pdmUserInfo.setUserDepartment(departmentName);
        pdmUserInfo.setPhoneNumber(phoneNumber);
        pdmUserInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        pdmUserInfo.setUserName(userName);
        pdmUserInfo.setUserId(iProductArchiveService.getUUId());
//      pdmUserInfo.setRole(role);//角色再说
        String status =userService.register(pdmUserInfo);
        return ResultUtil.success(status);

    }

    @RequestMapping(value = "/login")
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result login(@RequestParam(value = "account", required = true) String account,
                        @RequestParam(value = "passwd", required = true) String passwd){

        passwd = DigestUtils.md5DigestAsHex(passwd.getBytes());
        return ResultUtil.success( userService.loginByPasswd(account,passwd));
    }

    @RequestMapping(value = "/modifyPasswd")
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result modifyPasswd(@RequestParam(value = "userId", required = true) String userId,
                               @RequestParam(value = "oldPasswd", required = true) String oldPasswd,
                               @RequestParam(value = "newPasswd", required = true) String newPasswd){

        return ResultUtil.success(userService.modifyPasswdByAccount(userId,oldPasswd,newPasswd));
    }

    @RequestMapping(value = "/getUserList")
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getUserList(){

        return ResultUtil.success(userService.getUserList());
    }

}