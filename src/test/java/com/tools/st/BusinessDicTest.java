package com.tools.st;

import com.tools.st.entity.SysBaseBusinessType;
import com.tools.st.mapper.SysBaseBusinessTypeDao;
import com.tools.st.vo.BusinessTypeParam;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BusinessDicTest {

    @Autowired
    SysBaseBusinessTypeDao dao;

    private static List<BusinessTypeParam> newTypeParams = Lists.newArrayList(new BusinessTypeParam("检验类别", "testCategory"),
            new BusinessTypeParam("检验结果", "testResult"),
            new BusinessTypeParam("异常等级", "testExceptionGrade"),
            new BusinessTypeParam("异常分类", "testExceptionType"),
            new BusinessTypeParam("处置结果", "testDisposalResult"),
            new BusinessTypeParam("评审人员", "testReviewPerson")
    );

//
//    private SysBaseBusinessType createBusinessType(BusinessTypeParam param) {
//        SysBaseBusinessType type = new SysBaseBusinessType();
//        type.setName(param.getName());
//        type.setCode(param.getCode());
//        type.setRemark("");
//        type.setEnableflg(1);
//        type.setCreatedBy(1L);
//        type.setUpdatedBy(1L);
//        type.setCreatedTime(new Date());
//        type.setUpdatedTime(new Date());
//        return type;
//    }


}
