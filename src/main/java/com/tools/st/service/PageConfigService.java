package com.tools.st.service;

import com.tools.st.entity.SysComPageConfig;
import com.tools.st.entity.SysComTableConfig;
import com.tools.st.mapper.SysComPageConfigDao;
import com.tools.st.mapper.SysComTableConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PageConfigService {

    @Autowired
    private SysComPageConfigDao pageConfigDao;

    @Autowired
    private SysComTableConfigDao tableConfigDao;

    public List<SysComTableConfig> getTableConfig(@PathVariable String tableId) {
        return tableConfigDao.selectByTableId(tableId);
    }

    public List<SysComPageConfig> getPages() {
        List<SysComPageConfig> ret = pageConfigDao.findPages();
        return ret;
    }
}
