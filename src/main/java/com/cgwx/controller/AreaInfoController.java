package com.cgwx.controller;

import com.cgwx.aop.result.Result;
import com.cgwx.aop.result.ResultUtil;
import com.cgwx.data.dto.AreaInfoDto;
import com.cgwx.service.IAreaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping(value = "/Area")
public class AreaInfoController {
    @Autowired
    IAreaInfoService areaService;

    @GetMapping("/id={id}")
    @CrossOrigin(methods = RequestMethod.GET)

    public Result getPOIList(@PathVariable int id) throws IOException {

        List<AreaInfoDto> AreaDtoList =   areaService.getAreaListByParentId(id);
        return ResultUtil.success(AreaDtoList);

    }
}
