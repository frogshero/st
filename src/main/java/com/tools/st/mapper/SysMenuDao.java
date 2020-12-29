package com.tools.st.mapper;

import com.tools.st.mapper.base.GenericMapper;
import com.tools.st.entity.SysMenu;

public interface SysMenuDao extends GenericMapper<SysMenu, Long> {

    SysMenu selectByName(String name);

    Integer selectMaxOrderNum(Long parentId);
}