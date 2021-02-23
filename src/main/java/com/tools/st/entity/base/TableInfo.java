package com.tools.st.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tools.st.utl.DbToJavaUtl;
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
        return DbToJavaUtl.toJavaEntityName(tableName);
    }

    @JsonIgnore
    public String getShortName() {
        return StrUtl.getShortName(tableName);
    }

}
