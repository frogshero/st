package com.tools.st.idname.model;

import lombok.Data;

@Data
public class MoldMateriel extends IdNameEntityBase {
    private String materielCode;
    private String materielName;
    private String bomCode; //BOM编码
    private String drawingNumber;
    private String specification; //规格型号
}
