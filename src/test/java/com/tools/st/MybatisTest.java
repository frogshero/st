package com.tools.st;

import com.tools.st.entity.DbTableConfig;
import com.tools.st.mapper.DbTableConfigDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MybatisTest {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    //Mybatis批处理,拼接sql的方式不好
    @Transactional
    public void add(List<DbTableConfig> itemList) {
        //ExecutorType.BATCH
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        DbTableConfigDao mapper = session.getMapper(DbTableConfigDao.class);
        for (int i = 0; i < itemList.size(); i++) {
            mapper.insert(itemList.get(i));
            if (i % 1000 == 499) {//每500条提交一次防止内存溢出
                session.commit();
                session.clearCache();
            }
        }
        session.commit();
        session.clearCache();
    }
}
