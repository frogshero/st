package com.tools.st.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tools.st.utl.StrUtl;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableInfo {
    private String tableName;
    private String tableComment;

    @JsonIgnore
    public String getJavaName() {
        return StrUtl.toJavaClzName(tableName);
    }

    @JsonIgnore
    public String getShortName() {
        return StrUtl.getShortName(tableName);
    }

}
