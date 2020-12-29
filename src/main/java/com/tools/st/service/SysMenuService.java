package com.tools.st.service;

import com.tools.st.entity.SysMenu;
import com.tools.st.mapper.SysMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysMenuService {
    @Autowired
    SysMenuDao menuMapper;

    public SysMenu selectTopMenuByName(String name) {
        SysMenu menu = new SysMenu();
        menu.setName(name);
        menu.setParentId(0L);
        return menuMapper.selectByName(name);
    }

    public SysMenu selectSecondMenuByName(String name, Long parentId) {
        SysMenu menu = new SysMenu();
        menu.setName(name);
        menu.setParentId(parentId);
        return menuMapper.selectByName(name);
    }

    public Integer selectMaxOrder(Long parentId) {
        return menuMapper.selectMaxOrderNum(parentId);
    }

    public void insertMenu(SysMenu menu) {
        menuMapper.insert(menu);
    }
}
