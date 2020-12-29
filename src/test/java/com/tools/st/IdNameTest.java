package com.tools.st;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tools.st.entity.QcAbnormalTableVO;
import com.tools.st.idname.service.IdNameService;
import com.tools.st.mapper.QcAbnormalTableDao;
import com.tools.st.utl.JsonUtl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IdNameTest {
    @Autowired
    IdNameService idNameService;

    @Autowired
    QcAbnormalTableDao dao;

    @Test
    public void test() throws JsonProcessingException {
        List<QcAbnormalTableVO> data = dao.findList(new QcAbnormalTableVO());
        idNameService.setListName(data);

        log.info(JsonUtl.toJson(data));
    }
}
