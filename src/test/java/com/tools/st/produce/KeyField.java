package com.tools.st.produce;

import com.tools.st.utl.StrUtl;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KeyField {
    private String tabName;
    private String keyField;

    public String getTabShortName() {
        return StrUtl.getShortName(tabName);
    }
}
