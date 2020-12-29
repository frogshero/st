package com.tools.st.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
public class MybatisCreateParam {
    String tabName;
    String modelPackage;
    String daoPackage;
    String daoBase;
    String daoBasePackage;
    String voBase;
    String voBasePackage;
    String daoPostfix;

    List<String> ignoreList = Lists.newArrayList();
}
