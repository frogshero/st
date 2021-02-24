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
        //tabName后面设置
        param.setSchema("dlym_mes");
        param.setHomePackage("com.ymc.mes.mold.warehouse");
        param.setVoBasePackage("com.ymc.mes.mold.warehouse.common");
        param.setDaoBasePackage("com.ymc.mes.mold.warehouse.common");

        param.setDaoPostfix("Dao");
        param.setVoPostfix("VO");
        param.setVoBase("BaseBusinessVO");
//        param.setDaoBase("GenericDao");

        //for service,controller
        param.setNoField("WarehouseEntryNo");
        param.setEntityChinese("入库单");
        param.setRequestMapping("/mold/warehouse/entry");

        param.getIgnoreList().addAll(Lists.newArrayList("created_by", "updated_by", "enableflg", "created_time", "updated_time",
                "remark", "remark1", "remark2", "remark3", "remark4", "remark5",
                "remark6", "remark7", "remark8", "remark9", "remark10"));
        return param;
    }

    @Test
    public void generateAll() throws IOException {
        List<String> tables = Lists.newArrayList(
                "mold_warehouse_entry"
        );
        Resource templates = new ClassPathResource("/templates");
        STGroup group = new STGroupDir(templates.getFilename(), '$', '$');
        STGroup xmlGroup = new STGroupFile(new ClassPathResource("/templates/xml.stg").getFile().getCanonicalPath(), '$', '$');

        MybatisCreateParam param = getBasicParam();
        String baseDir = TestConst.BASE_DIR + "\\generate\\out";
        for (String tabName : tables) {
            param.setTabName(tabName);
            produceCodeByTable(group, xmlGroup, param, baseDir);
        }
    }

    @Test
    public void generateMainDetail() throws IOException {
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

    private void produceCodeByTable(STGroup group, STGroup xmlGroup, MybatisCreateParam param, String outDir) throws IOException {
        param.init();

        TableInfo tableInfo = tableInfoDao.getTableInfo(param.getSchema(), param.getTabName());
        assertNotNull(tableInfo);

        List<ColumnInfo> columns = tableInfoDao.getColumnInfos(param.getSchema(), param.getTabName());

        produceEntity(group, tableInfo, columns, param, outDir);

        produceDao(group, param, outDir);
//
        produceXmlMapper(xmlGroup, tableInfo, columns, param, outDir);

        produceService(group, param, outDir);

        produceController(group, param, outDir);

        produceControllerTest(group, param, outDir);
    }

    @Test
    public void testGroup() {
        Resource templates = new ClassPathResource("/templates2");
        STGroup group = new STGroupDir(templates.getFilename(), '$', '$');
        ST stMapper = group.getInstanceOf("mapper");
        Assertions.assertNotNull(stMapper);
    }

    private void produceDao(STGroup group, MybatisCreateParam param, String outDir) throws IOException {
        //dao不是文件名，是文件里定义的函数
        ST stMapper = group.getInstanceOf("dao");
        stMapper.add("param", param);
        FileUtl.writeStrToFile(stMapper.render(), outDir + "\\dao\\" + param.getDaoClzName() + ".java");
    }

    private void produceService(STGroup group, MybatisCreateParam param, String outDir) throws IOException {
        ST stMapper = group.getInstanceOf("service");
        stMapper.add("param", param);
        FileUtl.writeStrToFile(stMapper.render(), outDir + "\\service\\" + param.getJavaName() + "Service.java");
    }

    private void produceController(STGroup group, MybatisCreateParam param, String outDir) throws IOException {
        ST stMapper = group.getInstanceOf("controller");
        stMapper.add("param", param);
        FileUtl.writeStrToFile(stMapper.render(), outDir + "\\controller\\" + param.getJavaName() + "Controller.java");
    }

    private void produceControllerTest(STGroup group, MybatisCreateParam param, String outDir) throws IOException {
        ST stMapper = group.getInstanceOf("controllerTest");
        stMapper.add("param", param);
        FileUtl.writeStrToFile(stMapper.render(), outDir + "\\test\\" + param.getJavaName() + "Controller.java");
    }

    private void produceEntity(STGroup group, TableInfo tableInfo, List<ColumnInfo> columns, MybatisCreateParam param, String outDir) throws IOException {
        List<ColumnInfo> cols = Lists.newArrayList(columns);
        cols.removeIf(e -> param.getIgnoreList().indexOf(e.getName()) >= 0);

        Set<String> imports = Sets.newHashSet();
        for (ColumnInfo col : cols) {
            if (!col.getImport().startsWith("java.lang")) {
                imports.add(col.getImport());
            }
        }

        STGroup entityGroup = new STGroupFile(new ClassPathResource("/templates/entity.stg").getFile().getCanonicalPath(), '$', '$');
        ST stEntity = entityGroup.getInstanceOf("entity");
        stEntity.add("param", param);
        stEntity.add("tab", tableInfo);
        stEntity.add("cols", cols);
        stEntity.add("imports", imports);
        FileUtl.writeStrToFile(stEntity.render(), outDir + "\\model\\" + param.getJavaName() + ".java");

        ST voEntity = group.getInstanceOf("entityVO");
        voEntity.add("comment", tableInfo.getTableComment() + "列表VO");
        voEntity.add("clzName", param.getVoClzName());
        voEntity.add("baseClzName", param.getJavaName());
        voEntity.add("modelPackage", param.getModelPackage());
        FileUtl.writeStrToFile(voEntity.render(), outDir + "\\model\\" + param.getVoClzName() + ".java");

        voEntity = group.getInstanceOf("entityVO");
        voEntity.add("comment", tableInfo.getTableComment() + "的插入和修改参数");
        voEntity.add("clzName", param.getJavaName() + "Request");
        voEntity.add("modelPackage", param.getModelPackage());
        FileUtl.writeStrToFile(voEntity.render(), outDir + "\\model\\" + param.getJavaName() + "Request.java");
    }

    public void produceXmlMapper(STGroup xmlGroup, TableInfo tableInfo, List<ColumnInfo> columns, MybatisCreateParam param, String outDir) throws IOException {
        ColumnInfo keyCol = columns.stream().filter(e -> e.getKey()).findFirst().get();
        Assertions.assertNotNull(keyCol);

        //myxml不是文件名，是文件里定义的函数
        ST xmlST = xmlGroup.getInstanceOf("myxml");
        xmlST.add("param", param);
        xmlST.add("tab", tableInfo);
        xmlST.add("cols", columns);
        xmlST.add("keyCol", keyCol);
        FileUtl.writeStrToFile(xmlST.render(80), outDir + "\\dao\\" + tableInfo.getJavaName() + param.getDaoPostfix() + ".xml");
    }

}
