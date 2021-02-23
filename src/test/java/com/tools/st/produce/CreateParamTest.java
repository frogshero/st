package com.tools.st.produce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tools.st.utl.JsonUtl;
import com.tools.st.vo.MybatisCreateParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CreateParamTest {

    @Test
    public void test() throws JsonProcessingException {
        MybatisCreateParam param = new MybatisCreateParam();
        param.setTabName("sys_com_table_group");

        param.setSchema("dlym_mes");
        param.setModelPackage("com.ymc.mes.mold.warehouse.model");
        param.setDaoPackage("com.ymc.mes.mold.warehouse.dao");
        param.setVoBasePackage("com.ymc.mes.mold.warehouse.common");
        param.setDaoBasePackage("com.ymc.mes.mold.warehouse.common");
        param.setDaoPostfix("Dao");
        param.setVoPostfix("VO");
        param.setVoBase("BaseBusinessVO");
        param.setDaoBase("GenericDao");
        param.setNoField("WarehouseEntryNo");

        param.init();
        log.info(JsonUtl.toJson(param));
    }


}
