package com.tools.st.produce;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tools.st.TestConst;
import com.tools.st.entity.base.ColumnInfo;
import com.tools.st.entity.base.TableInfo;
import com.tools.st.mapper.base.TableInfoDao;
import com.tools.st.utl.FileUtl;
import com.tools.st.vo.MybatisCreateParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STGroupFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EntityGenerateTest {

    @Autowired
    TableInfoDao tableInfoDao;

    private MybatisCreateParam getBasicParam() {
        MybatisCreateParam param = new MybatisCreateParam();
        param.setSchema("dlym_mes");
//        param.setModelPackage("com.ymc.mes.qc.abnormal.model");
//        param.setDaoPackage("com.ymc.mes.qc.abnormal.dao");
//        param.setModelPackage("com.ymc.mes.basic.suppliercustomer.model");
        param.setModelPackage("com.ymc.mes.mold.warehouse.model");
//        param.setDaoPackage("com.ymc.mes.basic.suppliercustomer.dao");
        param.setDaoPackage("com.ymc.mes.mold.warehouse.dao");
//        param.setVoBasePackage("com.ymc.mes.basic.common");
        param.setVoBasePackage("com.ymc.mes.mold.warehouse.common");
//        param.setDaoBasePackage("com.ymc.mes.basic.system.dao");
        param.setDaoBasePackage("com.ymc.mes.mold.warehouse.common");

        param.setDaoPostfix("Dao");
        param.setVoPostfix("VO");
//        param.setVoBase("BaseBusinessVO");
        param.setVoBase("BaseBusinessVO");
//        param.setDaoBase("GenericMapper");
        param.setDaoBase("GenericDao");

        param.getIgnoreList().addAll(Lists.newArrayList("created_by", "updated_by", "enableflg", "created_time", "updated_time",
                "remark", "remark1", "remark2", "remark3", "remark4", "remark5",
                "remark6", "remark7", "remark8", "remark9", "remark10"));
        return param;
    }

    @Test
    public void generateAll() throws IOException {
        List<String> tables = Lists.newArrayList(
//            "sys_base_business_data_dict",
//            "sys_base_business_type",
//            "sys_com_table_select_config",
//            "sys_com_table_config",
//            "sys_com_page_api_config"
//            "sys_com_page_config",
//            "sys_menu",
//            "db_table_field_config",
//            "db_table_config",
//            "sys_com_table_group",
//            "sys_role_menu",
//            "sys_com_page_programme",
//            "qc_abnormal_audit",
//            "qc_abnormal_table",
//                "sys_com_page_programme_info",
//                "sys_com_page_programme_user",
//                "base_supplier_customer"
//                "mold_craft"
//                "mold_purchase_requisition",
//                "mold_purchase_requisition_detail"
//                "mold_craft_process"
//                "sys_base_business_data_dict"
//                "qc_abnormal_table"
//                "mold_purchase_requisition",
//                "mold_purchase_requisition_detail"
//                "mold_purchase_order",
//                "mold_craft_process"
                "mold_warehouse_entry"
        );
        Resource templates = new ClassPathResource("/templates");
        STGroup group = new STGroupDir(templates.getFilename(), '$', '$');
        STGroup entityGroup = new STGroupFile(new ClassPathResource("/templates/entity.stg").getFile().getCanonicalPath(), '$', '$');
        STGroup xmlGroup = new STGroupFile(new ClassPathResource("/templates/xml.stg").getFile().getCanonicalPath(), '$', '$');

        MybatisCreateParam param = getBasicParam();
        String baseDir = TestConst.BASE_DIR + "\\generate\\out";
        for (String tabName : tables) {
            param.setTabName(tabName);
            produceCodeByTable(group, entityGroup, xmlGroup, param, baseDir);
        }
    }

    @Test
    public void generateMainDetail() throws IOException {
//        String mainTab = "mold_purchase_requisition";
//        String detailTab = "mold_purchase_requisition_detail";
        String mainTab = "mold_purchase_cargo_received";
        String detailTab = "mold_purchase_cargo_received_detail";
        String schema = "";

        STGroup xmlGroup = new STGroupFile(new ClassPathResource("/templates/mainDetailXml.stg").getFile().getCanonicalPath(), '$', '$');

        TableInfo tableInfo = tableInfoDao.getTableInfo(schema, mainTab);
        TableInfo detailTableInfo = tableInfoDao.getTableInfo(schema, detailTab);

        List<ColumnInfo> columns = tableInfoDao.getColumnInfos(schema, mainTab);
        List<ColumnInfo> detailColumns = tableInfoDao.getColumnInfos(schema, detailTab);

        ColumnInfo keyCol = columns.stream().filter(e -> e.getKey()).findFirst().get();
        Assertions.assertNotNull(keyCol);
        String baseDir = TestConst.BASE_DIR + "\\generate\\out";

        ST xmlST = xmlGroup.getInstanceOf("mainDetailXml");
        xmlST.add("entityDir", baseDir);
        xmlST.add("entityPkg", "com.ymc.mes.mold.purchase.model");
        xmlST.add("tab", tableInfo);
        xmlST.add("detailTab", detailTableInfo);
        xmlST.add("cols", columns);
        xmlST.add("detailCols", detailColumns);
        xmlST.add("detailListField", "editTableList");
        xmlST.add("parentIdCol", "requisition_id");
        FileUtl.writeStrToFile(xmlST.render(80), baseDir + "\\md\\" + tableInfo.getJavaName() + ".xml");
    }

    private void produceCodeByTable(STGroup group, STGroup entityGroup, STGroup xmlGroup, MybatisCreateParam param, String outDir) throws IOException {
        TableInfo tableInfo = tableInfoDao.getTableInfo(param.getSchema(), param.getTabName());
        assertNotNull(tableInfo);

        List<ColumnInfo> columns = tableInfoDao.getColumnInfos(param.getSchema(), param.getTabName());

        produceEntity(entityGroup, tableInfo, columns, param, outDir);

        productDao(group, tableInfo, param, outDir);

        produceXmlMapper(xmlGroup, tableInfo, columns, param, outDir);
    }

    private void productDao(STGroup group, TableInfo tableInfo, MybatisCreateParam param, String outDir) throws IOException {
        ST stMapper = group.getInstanceOf("mapper");
        stMapper.add("entityPackage", param.getModelPackage());
        stMapper.add("entity", tableInfo.getJavaName());
        stMapper.add("daoBase", param.getDaoBase());
        stMapper.add("daoBasePkg", param.getDaoBasePackage());
        stMapper.add("postFix", param.getDaoPostfix());
        stMapper.add("voPostFix", param.getVoPostfix());
        FileUtl.writeStrToFile(stMapper.render(), outDir + "\\dao\\" + tableInfo.getJavaName() + param.getDaoPostfix() + ".java");
    }

    private void produceEntity(STGroup entityGroup, TableInfo tableInfo, List<ColumnInfo> columns, MybatisCreateParam param, String outDir) throws IOException {
        List<ColumnInfo> cols = Lists.newArrayList(columns);
        cols.removeIf(e -> param.getIgnoreList().indexOf(e.getName()) >= 0);

        Set<String> imports = Sets.newHashSet();
        for (ColumnInfo col : cols) {
            if (!col.getImport().startsWith("java.lang")) {
                imports.add(col.getImport());
            }
        }
        ST stEntity = entityGroup.getInstanceOf("entity");
        stEntity.add("entityPackage", param.getModelPackage());
        stEntity.add("parentPkg", param.getVoBasePackage());
        stEntity.add("parent", param.getVoBase());
        stEntity.add("tab", tableInfo);
        stEntity.add("cols", cols);
        stEntity.add("voPostFix", param.getVoPostfix());
        stEntity.add("imports", imports);
        FileUtl.writeStrToFile(stEntity.render(), outDir + "\\model\\" + tableInfo.getJavaName() + param.getVoPostfix() + ".java");
    }

    public void produceXmlMapper(STGroup xmlGroup, TableInfo tableInfo, List<ColumnInfo> columns, MybatisCreateParam param, String outDir) throws IOException {
        ColumnInfo keyCol = columns.stream().filter(e -> e.getKey()).findFirst().get();
        Assertions.assertNotNull(keyCol);

        ST xmlST = xmlGroup.getInstanceOf("myxml");
        xmlST.add("entityDir", param.getModelPackage());
        xmlST.add("daoDir", param.getDaoPackage());

        xmlST.add("tab", tableInfo);
        xmlST.add("cols", columns);
        xmlST.add("keyCol", keyCol);
        xmlST.add("postFix", param.getDaoPostfix());
        xmlST.add("voPostfix", param.getVoPostfix());
        FileUtl.writeStrToFile(xmlST.render(80), outDir + "\\dao\\" + tableInfo.getJavaName() + param.getDaoPostfix() + ".xml");
    }

}
