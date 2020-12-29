package com.tools.st.mapper;

import com.tools.st.mapper.base.GenericMapper;
import com.tools.st.entity.SysComPageApiConfig;

import java.util.List;

public interface SysComPageApiConfigDao extends GenericMapper<SysComPageApiConfig, Long> {
    List<SysComPageApiConfig> selectByPageConfigId(Long configId);
}