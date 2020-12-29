package com.tools.st.produce;

import com.tools.st.idname.enums.DataTableEnum;
import lombok.Data;

@Data
public class FieldDbInfo {
    String fieldName;
    String columnName;
    DataTableEnum tableEnum;
}
