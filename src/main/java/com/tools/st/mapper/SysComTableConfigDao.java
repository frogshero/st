package com.tools.st.mapper;

import com.tools.st.mapper.base.GenericMapper;
import com.tools.st.entity.SysComTableConfig;

import java.util.List;

public interface SysComTableConfigDao extends GenericMapper<SysComTableConfig, Long> {
    List<SysComTableConfig> selectByTableId(String tableId);

    List<SysComTableConfig> selectBy123();

    SysComTableConfig selectBy234(Long id);
}