package com.tools.st.mapper;

import com.tools.st.mapper.base.GenericMapper;
import com.tools.st.entity.SysComTableSelectConfig;

import java.util.List;

public interface SysComTableSelectConfigDao extends GenericMapper<SysComTableSelectConfig, Long> {
    List<SysComTableSelectConfig> selectByTableId(String tableId);
}