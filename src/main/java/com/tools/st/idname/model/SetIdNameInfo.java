package com.tools.st.idname.model;

import com.tools.st.idname.enums.DataTableEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetIdNameInfo {
    String idMethod;
    String setMethod;
    String srcMethod;
    DataTableEnum tableEnum;
    String keyPrefix;
    Integer order;
}
