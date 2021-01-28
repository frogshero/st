package com.tools.st.tableConfig.dao;


import com.tools.st.mapper.base.GenericMapper;
import com.tools.st.tableConfig.SysComTableConfigVO;

import java.util.List;

public interface SysComTableConfigDao extends GenericMapper<SysComTableConfigVO, Long> {
    List<SysComTableConfigVO> selectByTableId(String tableId);
}