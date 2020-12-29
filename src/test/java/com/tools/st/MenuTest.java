package com.tools.st;

import com.tools.st.entity.SysMenu;
import com.tools.st.entity.SysRoleMenu;
import com.tools.st.mapper.SysMenuDao;
import com.tools.st.mapper.SysRoleMenuDao;
import com.tools.st.service.SysMenuService;
import com.tools.st.vo.BusinessTypeParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MenuTest {

    @Autowired
    SysMenuDao menuMapper;

    @Autowired
    SysMenuService menuService;

    @Autowired
    SysRoleMenuDao roleMenuMapper;

    @Test
    public void testSel() {
        SysMenu menu = menuMapper.selectByPrimaryKey(891L);
        assertThat(menu.getName()).isEqualTo("计划管理");
    }

    @Test
    public void testAdd3LevelMenu() {
//        List<BusinessTypeParam> newTypeParams = Lists.newArrayList(new BusinessTypeParam("检验类别", "testCategory"),
//                new BusinessTypeParam("检验结果", "testResult"),
//                new BusinessTypeParam("异常等级", "testExceptionGrade"),
//                new BusinessTypeParam("异常分类", "testExceptionType"),
//                new BusinessTypeParam("处置结果", "testDisposalResult"),
//                new BusinessTypeParam("评审人员", "testReviewPerson")
//        );
//        addSubMenu("质量管理", "基础数据", newTypeParams);

        addSubMenu("质量管理", "",
                Lists.newArrayList(new BusinessTypeParam("质量检验单", "qcAbnormalTable")));
    }

    private List<SysMenu> prepareMenu(Long parentId, Integer maxOrder, List<BusinessTypeParam> newTypeParams) {
        List<SysMenu> newEntities = Lists.newArrayList();
        Integer order = maxOrder;
        for (BusinessTypeParam typeParam : newTypeParams) {
            order++;
            newEntities.add(createMenuData(parentId, typeParam.getName(), "/page/page?pageId=" + typeParam.getCode() + "List", order));
        }
        return newEntities;
    }

    private SysMenu createMenuData(Long parentId, String name, String url, Integer order) {
        SysMenu menu = new SysMenu();
        menu.setParentId(parentId);
        menu.setOrderNum(order);
        menu.setName(name);
        menu.setUrl(url);
        menu.setEnableflg(1);
        menu.setLevel("0"); //???
        menu.setComId(1L);
        menu.setType(0);
        menu.setMenuType("0");
        menu.setShowFlg(1);
        menu.setCreatedBy(1L);
        menu.setCreatedTime(new Date());
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy(1L);
        return menu;
    }

    private void addSubMenu(String toMenuName, String secondMenuName, List<BusinessTypeParam> newTypeParams) {
        SysMenu topMenu = menuService.selectTopMenuByName(toMenuName);
        assertThat(topMenu).isNotNull();

        SysMenu secondMenu = topMenu;
        if (StringUtils.isNotBlank(secondMenuName)) {
            secondMenu = menuService.selectSecondMenuByName(secondMenuName, topMenu.getMenuId());
            assertThat(secondMenu).isNotNull();
        }

        Integer maxOrder = menuService.selectMaxOrder(secondMenu.getMenuId());
        List<SysMenu> newMenus = prepareMenu(secondMenu.getMenuId(), maxOrder, newTypeParams);

        for (SysMenu menu : newMenus) {
            menuService.insertMenu(menu);
            //sys_role_menu 还要插入权限
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menu.getMenuId());
            roleMenu.setRoleId(1L); //admin
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Data
    @AllArgsConstructor
    private static class MenuParam {
        private String name;
        private String url;
    }

    @Test
    public void addMenu() {
        addSysMenu(1L, 2351L, "质量检验单-审核", "/page/page?pageId=qcAbnormalAuditList", 4);
    }

    @Test
    public void selMenu() {
        SysMenu menu = menuMapper.selectByPrimaryKey(2352L);
        Assertions.assertNotNull(menu);
    }

    @Test
    public void delMenu() {
        menuMapper.deleteByPrimaryKey(2403L);
    }

    @Test
    public void updateMenu() {
        Long menuId = addSysMenu(1L, 2351L, "质量检验单-审核", "/page/page?pageId=qcAbnormalAuditList", 4);
        SysMenu menu = menuMapper.selectByPrimaryKey(menuId);
        menu.setName("TEST1234");
        menuMapper.updateByPrimaryKey(menu);
    }

    private Long addSysMenu(Long comId, Long parentId, String name, String url, Integer orderNum) {
        SysMenu menu = new SysMenu();
        menu.setComId(comId);
        menu.setParentId(parentId);
        menu.setName(name);
        menu.setUrl(url);
        menu.setOrderNum(orderNum);

        //menu.setMenuId(); //auto inc
        menu.setIcon(null);
        menu.setLevel("0");
        menu.setShowFlg(1);
        menu.setEnableflg(1);
        menu.setMenuType("0");
        menu.setType(1);
        menu.setCreatedBy(1L);
        menu.setCreatedTime(new Date());
        menu.setUpdatedBy(1L);
        menu.setUpdatedTime(new Date());

        menuMapper.insert(menu);

        return menu.getMenuId();
    }
}
