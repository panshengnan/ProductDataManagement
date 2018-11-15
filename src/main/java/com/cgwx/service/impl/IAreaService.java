package com.cgwx.service.impl;

import com.cgwx.data.dto.AreaInfoDto;

import java.io.IOException;
import java.util.List;

public interface IAreaService {
    List<AreaInfoDto> getAreaListByParentId(Integer parentId) throws IOException;
}
