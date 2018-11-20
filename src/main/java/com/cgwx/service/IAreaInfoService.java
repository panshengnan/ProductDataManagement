package com.cgwx.service;

import com.cgwx.data.dto.AreaInfoDto;

import java.io.IOException;
import java.util.List;

public interface IAreaInfoService {
    List<AreaInfoDto> getAreaListByParentId(Integer parentId) throws IOException;
}
