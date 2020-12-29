package com.tools.st.mapper;

import com.tools.st.mapper.base.GenericMapper;
import com.tools.st.entity.SysComPageConfig;

import java.util.List;

public interface SysComPageConfigDao extends GenericMapper<SysComPageConfig, Long> {

    SysComPageConfig findByPageId(String pageId);

    List<SysComPageConfig> findPages();
}