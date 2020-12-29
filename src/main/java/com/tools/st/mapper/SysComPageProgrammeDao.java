package com.tools.st.mapper;

import com.tools.st.mapper.base.GenericMapper;
import com.tools.st.entity.SysComPageProgramme;

import java.util.List;

public interface SysComPageProgrammeDao extends GenericMapper<SysComPageProgramme, Long> {
    List<SysComPageProgramme> selectByConfigId(Long configId);
}