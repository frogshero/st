package com.tools.st.mapper;

import com.tools.st.mapper.base.GenericMapper;
import com.tools.st.entity.SysComTableGroup;

import java.util.List;

public interface SysComTableGroupDao extends GenericMapper<SysComTableGroup, Long> {
    List<SysComTableGroup> selectByTableId(String pageId);
}