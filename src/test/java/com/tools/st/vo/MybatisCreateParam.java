package com.tools.st.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
public class MybatisCreateParam {
    String schema;
    String tabName;
    String modelPackage;
    String daoPackage;
    String daoBase;
    String daoBasePackage;
    String voBase;
    String voBasePackage;
    String daoPostfix;
    String voPostfix;

    List<String> ignoreList = Lists.newArrayList();
}
