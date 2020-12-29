package com.tools.st;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.tools.st.entity.*;
import com.tools.st.entity.base.ColumnInfo;
import com.tools.st.enums.PageAddEditType;
import com.tools.st.enums.PageType;

import static org.assertj.core.api.Assertions.*;

import com.tools.st.mapper.*;
import com.tools.st.mapper.base.TableInfoDao;
import com.tools.st.utl.BeanUtil;
import com.tools.st.utl.DbToJavaUtl;
import com.tools.st.utl.JsonUtl;
import com.tools.st.vo.BusinessTypeParam;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PageTest {
    @Autowired
    SysComPageConfigDao pageConfigDao;

    @Autowired
    SysComPageApiConfigDao apiConfigDao;

    @Autowired
    SysComTableConfigDao tableConfigDao;

    @Autowired
    SysComTableSelectConfigDao selectConfigDao;

    @Autowired
    SysComTableGroupDao groupDao;

    @Autowired
    TableInfoDao tableInfoDao;

    @Autowired
    SysComPageProgrammeDao programmeDao;

    @Test
    public void test123() {
        List<SysComTableConfig> list = tableConfigDao.selectBy123();
        for (SysComTableConfig config : list) {
            SysComTableConfig old = tableConfigDao.selectBy234(config.getId());
            if (old != null) {
                config.setSearchApi(old.getSearchApi());
                config.setComponentsResultList(old.getComponentsResultList());
                config.setTitleDetailAboutId(old.getTitleDetailAboutId());
                tableConfigDao.updateByPrimaryKey(config);
            }
        }
    }

    private static List<String> IGNORE_SHOW_LIST = Lists.newArrayList("id", "com_Id", "created_by",
            "created_time", "enableflg");
//    private static String FROM_LIST = "userList";
//    private static String TO_LIST = "testList";


    private static final String IF_TEST = "<if test=\"%s!=null\"> inner join sys_user on abnormal.%s=%s.user_id </if>";
    @Test
    public void testFormat() {
        List<SysComTableSelectConfig> configs = selectConfigDao.selectByTableId("qcAbnormalList");
        List<SysComTableSelectConfig> userList = configs.stream().filter(e -> StringUtils.hasText(e.getComponentsId())
                && e.getComponentsId().equals("selectUserList")).collect(Collectors.toList());

        for (SysComTableSelectConfig config : configs) {
            if (StringUtils.hasText(config.getComponentsId()) && config.getComponentsId().equals("selectUserList")) {
                String idField = config.getTitleAccept().split(",")[0];
                System.out.println(String.format(IF_TEST, config.getTableTitle(), idField, idField+"U"));
            }
        }
    }

    @Test
    public void testAddPage() {
        addPage(1L, "qcAbnormalAuditList", "质量检验单-评审");
    }

    private void addPage(Long comId, String pageId, String title) {
        SysComPageConfig pageConfig = new SysComPageConfig();
        pageConfig.setComId(comId);
        pageConfig.setPageId(pageId);
        pageConfig.setPageTitleName(title);
//        pageConfig.setPageProgrammeId();
        pageConfig.setPageType(PageType.stand.getValue());
//        pageConfig.setPageComponents();
//        pageConfig.setTreeCode();
        pageConfig.setAddType(PageAddEditType.stand.getValue());
        pageConfig.setEnableflg(1);
        pageConfig.setCreatedBy(1L);
        pageConfig.setUpdatedBy(1L);
        pageConfig.setCreatedTime(new Date());
        pageConfig.setUpdatedTime(new Date());
//        pageConfig.setAcceptCode();

        pageConfigDao.insert(pageConfig);
    }

    @Test
    public void testDel() {
        delByPageId("testCategoryList");
    }

    private void delByPageId(String pageId) {
        SysComPageConfig pageConfig = pageConfigDao.findByPageId(pageId);
        assertThat(pageConfig).isNotNull();
        pageConfigDao.deleteByPrimaryKey(pageConfig.getId());

        List<SysComPageApiConfig> apiConfigs = apiConfigDao.selectByPageConfigId(pageConfig.getId());
        apiConfigs.stream().forEach(e -> apiConfigDao.deleteByPrimaryKey(e.getId()));

        List<SysComTableConfig> tableConfigs = tableConfigDao.selectByTableId(pageConfig.getPageId());
        tableConfigs.stream().forEach(e -> tableConfigDao.deleteByPrimaryKey(e.getId()));

        List<SysComTableSelectConfig> selectConfigs = selectConfigDao.selectByTableId(pageConfig.getPageId());
        selectConfigs.stream().forEach(e -> selectConfigDao.deleteByPrimaryKey(e.getId()));

        List<SysComTableGroup> groups = groupDao.selectByTableId(pageConfig.getPageId());
        groups.stream().forEach(e -> groupDao.deleteByPrimaryKey(e.getId()));
    }

    @Ignore
    @Test
    public void saveFromConfig() throws IOException {
        //pageConfigDao
        Long id = 132L;
        String pageId = "qcAbnormalList";
        List<SysComPageApiConfig> apiConfigs = apiConfigDao.selectByPageConfigId(id);
        JsonUtl.toFile(new File(TestConst.TEST_SOURCE + "\\data\\PageApiConfig_"+pageId+".json"), apiConfigs);

        List<SysComTableConfig> tableConfigs = tableConfigDao.selectByTableId(pageId);
        JsonUtl.toFile(new File(TestConst.TEST_SOURCE + "\\data\\TableConfig_"+pageId+".json"), tableConfigs);

        List<SysComTableSelectConfig> selectConfigs = selectConfigDao.selectByTableId(pageId);
        JsonUtl.toFile(new File(TestConst.TEST_SOURCE + "\\data\\TableSelectConfig_"+pageId+".json"), selectConfigs);

    }

    //sort_no排序
    @Test
    public void testSort() {
        List<String> sort = Lists.newArrayList("订单号", "下单日期", "订单交期", "采购类型", "备注", "供应商", "供应商代码", "采购员", "采购员代码", "合同编号", "申购部门", "申购部门代码", "订单金额", "维护人", "维护人代码", "维护时间", "物料编码", "物料名称", "规格", "材质", "材质代码", "硬度", "硬度代码", "单位", "单位代码", "数量", "工序", "单价", "金额", "备注", "预交日期", "供应商", "供应商代码", "制造编号", "产品编号", "产品名称", "零件编码", "零件名称", "零件规格", "零件材质", "零件材质代码", "请购单号", "发票号码");
        List<SysComTableSelectConfig> selectConfigs = selectConfigDao.selectByTableId("purchaseOrderList");
        for (SysComTableSelectConfig config : selectConfigs) {
            config.setSortNo(sort.indexOf(config.getTableTitleName()) * 10);
        }
        for (SysComTableSelectConfig config : selectConfigs) {
            selectConfigDao.updateByPrimaryKey(config);
        }
    }

    @Test
    public void copyConfig() throws IOException {
//        delByPageId("testList");
        addPageConfig("userList", "testList", "测试");

    }

    @Test
    public void addGroup() throws Exception {
        String pageId = "qcAbnormalAuditList";
        List<String> groups = Lists.newArrayList("基本信息", "异常信息", "异常损失", "其他信息");
        List<String> groupShow = Lists.newArrayList("1", "1", "0", "0");

        for (int i = 0; i < groups.size(); i++) {
            SysComTableGroup group = new SysComTableGroup();
            group.setTableId(pageId);
            group.setGroupName(groups.get(i));
            group.setSortNo(String.valueOf(i));
            group.setInitShowFlg(groupShow.get(i));
            BeanUtil.setAuditFields(group);
            groupDao.insert(group);
        }
    }

    @Test
    public void addTableSelectConfig() throws Exception {
        String tabId = "qcAbnormalAuditList";
        List<SysComTableConfig> tableConfigs = tableConfigDao.selectByTableId(tabId);
        for (int i=0; i<tableConfigs.size(); i++) {
            SysComTableConfig col = tableConfigs.get(i);

            SysComTableSelectConfig selectConfig = new SysComTableSelectConfig();
            selectConfig.setTableTitleName(col.getTableTitleName());
            selectConfig.setTableTitle(col.getTableTitle());
            selectConfig.setSelectKet("audit." + DbToJavaUtl.toDbColName(col.getTableTitle()));
            selectConfig.setTableId(tabId);
            selectConfig.setSortNo(i);

            if (col.getTitleType().equals("text") || col.getTitleType().equals("label")) {
                selectConfig.setTitleType("input");
            } else if (col.getTitleType().equals("dateTime")) {
                selectConfig.setTitleType("date");
            } else if (col.getTitleType().equals("popups")) {
                selectConfig.setTitleType("components");
            } else {
                selectConfig.setTitleType(col.getTitleType());
            }
            selectConfig.setComponentsName(col.getComponentsName());
            selectConfig.setComponentsType(col.getComponentsType());
            selectConfig.setComponentsResult(col.getComponentsResultList());
            selectConfig.setTableKey(col.getTableKey());
            selectConfig.setKeyCode(col.getKeyCode());
            selectConfig.setTitleAccept(col.getTitleDetailAboutId());
            selectConfig.setComponentsId(col.getTitleDetail());
            selectConfig.setSearchApi(col.getSearchApi());

            BeanUtil.setAuditFields(selectConfig);

            selectConfigDao.insert(selectConfig);
        }
    }

    @Test
    public void resetOrder() throws Exception {
        String tabId = "materielListSelect";
        List<SysComTableConfig> columns = tableConfigDao.selectByTableId(tabId);

        //selectConfig按照tableConfig排序
        List<SysComTableSelectConfig> selectConfigs = selectConfigDao.selectByTableId(tabId);
        for (SysComTableSelectConfig selectConfig : selectConfigs) {
            Optional<SysComTableConfig> col = columns.stream().filter((e) -> e.getTableTitle().equals(selectConfig.getTableTitle())).findFirst();
            if (col.isPresent() && col.get().getOrderBy() != null) {
                selectConfig.setSortNo((col.get().getOrderBy() - 100) * 10);
                selectConfigDao.updateByPrimaryKey(selectConfig);
            }

        }
        //
//        for (int i = 0; i < columns.size(); i++) {
//            SysComTableConfig col = columns.get(i);
//            if (col.getGroupId() == null || col.getGroupId() <=0) continue;
//            col.setOrderBy(col.getGroupId().intValue() * 100 + i);
//            tableConfigDao.updateByPrimaryKey(col);
//        }
    }

    /**
     * Comment: title,表名,类型名
     * @throws Exception
     */
    @Test
    public void addTableConfig() throws Exception {
        String tabId = "qcAbnormalAuditList";
        List<ColumnInfo> columns = tableInfoDao.getColumnInfos(TestConst.SCHEMA, "qc_abnormal_audit");
        for (int i=0; i<columns.size(); i++) {
            ColumnInfo col = columns.get(i);
            if (IGNORE_SHOW_LIST.indexOf(col.getName()) >=0) continue;

            SysComTableConfig tableConfig = new SysComTableConfig();
            tableConfig.setTableId(tabId);
            initTableConfig(tabId, col, i, tableConfig);
            tableConfigDao.insert(tableConfig);

            if (col.getComment().split(",").length > 1) {
//                tableConfig = new SysComTableConfig();
//                initTableConfig(tabId, col, i, tableConfig);
                tableConfig.setId(null);

                if (col.getComment().indexOf("sys_user") > 0) {
                    tableConfig.setTableTitle(col.getJavaName().replace("Id", "Name"));
                    tableConfig.setTitleType("popups");
                    tableConfig.setTitleDetail("mesbasic/sys/user/getUserList?tableId=selectUserList");
                    tableConfig.setTitleDetailAboutId(col.getJavaName() + "," + col.getJavaName().replace("UserId", "UserName"));
                    tableConfig.setComponentsId("selectUserList");
                    tableConfig.setComponentsName("选择" + col.getComment().split(",")[0]);
                    tableConfig.setComponentsType("1");
                    tableConfig.setComponentsResultList("userId,name");
                } else if (col.getComment().indexOf("mold_craft_process") > 0) {
                    tableConfig.setTableTitle(col.getJavaName().replace("Id", "Name"));
                    tableConfig.setTitleType("popups");
                    tableConfig.setTitleDetail("mesbasic/system/dynamic/api/add?tableId=processManagement");
                    tableConfig.setTitleDetailAboutId(col.getJavaName() + "," + col.getJavaName().replace("ProcessId", "ProcessName"));
                    tableConfig.setComponentsId("selectProcessM");
                    tableConfig.setComponentsName("选工序");
                    tableConfig.setComponentsType("0");
                    tableConfig.setComponentsResultList("processId,processName");
                } else if (col.getComment().indexOf("base_materiel") > 0) {
                    tableConfig.setTableTitle(col.getJavaName().replace("Id", "Name"));
                    tableConfig.setTitleType("popups");
                    tableConfig.setTitleDetail("mesmold/mold/bom/selectBomList?tableId=selectParts");
                    tableConfig.setTitleDetailAboutId("materielId,partNo,partName,productId");
                    tableConfig.setComponentsId("selectParts");
                    tableConfig.setComponentsName("选择零件");
                    tableConfig.setComponentsType("1");
                    tableConfig.setComponentsResultList("bomMaterielId,bomMaterielCode,bomMaterielName");
                }
                tableConfigDao.insert(tableConfig);
            }
        }
    }

    private void initTableConfig(String tabId, ColumnInfo col, int i, SysComTableConfig tableConfig) throws Exception {
        tableConfig.setTableId(tabId);
        tableConfig.setTableTitle(col.getJavaName());
        tableConfig.setTableTitleName(col.getComment().split(",")[0]);
        tableConfig.setLength("100");
        tableConfig.setOrderBy(i * 10);
        tableConfig.setIsNull("1");
        tableConfig.setEditFlg("1");
        tableConfig.setShowFlg("1");
        tableConfig.setSysFlg("0");
        tableConfig.setAddFlg("1");
        tableConfig.setSortFlg("1");
        tableConfig.setGroupId(0L);
        tableConfig.setAlignmentMode("3");
        tableConfig.setAddHiddenFlg("0");
        tableConfig.setTitleType("text");
        tableConfig.setTitleWidth("60");
        tableConfig.setComponentsType("1");
        if (col.getName().startsWith("date")) {
            tableConfig.setTitleType("dateTime");
            tableConfig.setInitValue("now");
        }
        if (col.getComment().indexOf("sys_dict") > 0) {
            tableConfig.setTableTitle(col.getJavaName() + "Name");
            tableConfig.setTitleType("radio");
            tableConfig.setTitleDetail("mesbasic/sys/dict/info/getDictList?type=" + col.getComment().split(",")[2]);
            tableConfig.setTitleDetailAboutId(col.getJavaName());
            tableConfig.setComponentsResultList("value,name");
        }
        if (col.getComment().indexOf("sys_base_business_data_dict") > 0) {
            tableConfig.setTableTitle(col.getJavaName() + "Name");
            tableConfig.setTitleType("select");
            tableConfig.setTitleDetail("mesbasic/sys/base/business/data/findList?businessType=" + col.getComment().split(",")[2]);
            tableConfig.setTitleDetailAboutId(col.getJavaName());
            tableConfig.setComponentsId(col.getComment().split(",")[2] + "List");
            tableConfig.setComponentsType("1");
            tableConfig.setComponentsResultList("id,name");
        }
        BeanUtil.setAuditFields(tableConfig);
    }

    @Test
    public void copyFromSpecified() {
        String fromTableId = "testCategoryList";
        String oldApiParam = "testCategory";
        List<BusinessTypeParam> newTypeParams = Lists.newArrayList(//new BusinessTypeParam("检验类别", "testCategory")
                //,
                new BusinessTypeParam("检验结果", "testResult"),
                new BusinessTypeParam("异常等级", "testExceptionGrade"),
                new BusinessTypeParam("异常分类", "testExceptionType"),
                new BusinessTypeParam("处置结果", "testDisposalResult"),
                new BusinessTypeParam("评审人员", "testReviewPerson")
        );

        SysComPageConfig pageConfig = pageConfigDao.findByPageId(fromTableId);
        assertThat(pageConfig).isNotNull();

        newTypeParams.stream().forEach(e -> {
            String tableId = e.getCode() + "List";

            List<SysComPageApiConfig> apiConfigs = apiConfigDao.selectByPageConfigId(pageConfig.getId());
            assertThat(apiConfigs.size()).isGreaterThan(0);

            List<SysComTableConfig> tableConfigs = tableConfigDao.selectByTableId(fromTableId);
            assertThat(tableConfigs.size()).isGreaterThan(0);

            List<SysComTableSelectConfig> selectConfigs = selectConfigDao.selectByTableId(fromTableId);
            assertThat(selectConfigs.size()).isGreaterThan(0);
            List<SysComTableGroup> groups = groupDao.selectByTableId(fromTableId);

            List<SysComPageProgramme> programmes = programmeDao.selectByConfigId(pageConfig.getId());
            assertThat(programmes.size()).isGreaterThan(0);
            //下面会修改list里面的值，所以必须循环内查
            addPage(1L, tableId, e.getName());

            copyPageDetailFromData(tableId, oldApiParam, e.getCode(), apiConfigs, selectConfigs, groups, tableConfigs, programmes);
        });
    }

    @Test
    public void copyButton() {
        String fromTableId = "userList";
        String toTableId = "qcAbnormalAuditList";
        String oldApiParam = "userList";

        SysComPageConfig pageConfig = pageConfigDao.findByPageId(fromTableId);
        assertThat(pageConfig).isNotNull();

        List<SysComPageApiConfig> apiConfigs = apiConfigDao.selectByPageConfigId(pageConfig.getId());
        assertThat(apiConfigs.size()).isGreaterThan(0);

        List<SysComPageProgramme> programmes = programmeDao.selectByConfigId(131L);
        assertThat(programmes.size()).isGreaterThan(0);

        copyPageDetailFromData(toTableId, oldApiParam, toTableId, apiConfigs, null, null, null, programmes);
    }

    private void copyPageDetailFromData(String tableId,
                                        String oldApiParam,
                                        String newApiParam,
                                        List<SysComPageApiConfig> apiConfigs,
                                        List<SysComTableSelectConfig> selectConfigs,
                                        List<SysComTableGroup> oldGrp,
                                        List<SysComTableConfig> tableConfigs,
                                        List<SysComPageProgramme> programmes) {
        SysComPageConfig pageConfig = pageConfigDao.findByPageId(tableId);
        assertThat(pageConfig).isNotNull();

        if (apiConfigs != null) {
            for (SysComPageApiConfig apiConfig : apiConfigs) {
                apiConfig.setPageConfigId(pageConfig.getId());
                apiConfig.setCreatedTime(new Date());
                String pageApi = apiConfig.getPageApi();
                if (StringUtils.hasText(pageApi)) {
                    apiConfig.setPageApi(pageApi.replaceAll(oldApiParam, newApiParam));
                }
                apiConfigDao.insert(apiConfig);
            }
        }

        if (selectConfigs != null) {
            selectConfigs.stream().forEach(e -> {
                e.setTableId(tableId);
                e.setCreatedTime(new Date());
                selectConfigDao.insert(e);
            });
        }

        //插入后ID是新的，保存下旧的
        if (oldGrp != null) {
            List<Long> oldList = oldGrp.stream().map(SysComTableGroup::getId).collect(Collectors.toList());
            oldGrp.stream().forEach(e -> {
                e.setTableId(tableId);
                e.setCreatedTime(new Date());
                groupDao.insert(e);
            });
            for (SysComTableConfig config : tableConfigs) {
                config.setTableId(tableId);
                //新的组号
                int pos = oldList.indexOf(config.getGroupId());
                if (pos >= 0) {
                    config.setGroupId(oldGrp.get(pos).getId());
                }
                config.setCreatedTime(new Date());
                tableConfigDao.insert(config);
            }
        }

        if (programmes != null) {
            programmes.stream().forEach(e -> {
                e.setPageConfigId(pageConfig.getId());
                e.setCreatedTime(new Date());
                programmeDao.insert(e);
            });
        }
    }

    private void addPageConfig(String fromTableId, String toTableId, String title) throws IOException {
        ObjectMapper om = new ObjectMapper();
        List<SysComPageApiConfig> apiConfigs = om.readValue(new File(TestConst.BASE_SOURCE + "\\data\\SysComPageApiConfig.json"),
                new TypeReference<List<SysComPageApiConfig>>() {
                });


        List<SysComTableSelectConfig> selectConfigs = om.readValue(new File(TestConst.BASE_SOURCE + "\\data\\SysComTableSelectConfig.json"),
                new TypeReference<List<SysComTableSelectConfig>>() {
                });


        List<SysComTableGroup> oldGrp = om.readValue(new File(TestConst.BASE_SOURCE + "\\data\\SysComTableGroup.json"),
                new TypeReference<List<SysComTableGroup>>() {
                });

        //新的groupId
        List<SysComTableConfig> tableConfigs = om.readValue(new File(TestConst.BASE_SOURCE + "\\data\\SysComTableConfig.json"),
                new TypeReference<List<SysComTableConfig>>() {
                });

        copyPageDetailFromData(toTableId, fromTableId, toTableId, apiConfigs, selectConfigs, oldGrp, tableConfigs, Lists.newArrayList());
    }

}
