package com.tools.st.idname.repo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "idname.cache")
public class IdNameConfiguration {
    boolean batchSelect = true;

    Integer orderMaxSize = 200;
    Integer orderDuration = 24;

    Integer userMaxSize = 500;
    Integer userDuration = 24;

    Integer SysDictMaxSize = 200;
    Integer SysDictDuration = 24;

    Integer processMaxSize = 200;
    Integer processDuration = 24;

    Integer businessDictMaxSize = 500;
    Integer businessDictDuration = 24;

    Integer deptMaxSize = 50;
    Integer deptDuration = 24;

    Integer materielMaxSize = 1000;
    Integer materielDuration = 24;

}
