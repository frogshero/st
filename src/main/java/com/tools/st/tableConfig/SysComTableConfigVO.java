package com.tools.st.tableConfig;

import com.tools.st.entity.EntityBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "表格配置")
@Data
public class SysComTableConfigVO extends EntityBase {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("上级集团/公司 0独立公司 ")
    private Long comId;

    @ApiModelProperty("表格编号")
    private String tableId;

    @ApiModelProperty("标题")
    private String tableTitle;

    @ApiModelProperty("表头名字（为空时，展示表整体设置的信息）")
    private String tableTitleName;

    @ApiModelProperty("组别")
    private Long groupId;

    @ApiModelProperty("表key")
    private String tableKey;

    @ApiModelProperty("表字段code")
    private String keyCode;

    @ApiModelProperty("是否显示")
    private String showFlg;

    @ApiModelProperty("标题宽度")
    private Integer titleWidth;

    @ApiModelProperty("排序")
    private Integer orderBy;

    @ApiModelProperty("是否排序")
    private String sortFlg;

    @ApiModelProperty("标题格式：text, textarea, img, file, button, checkbox, radio, select,number,popups,label")
    private String titleType;

    @ApiModelProperty("标题列表详细（设置接口地址）")
    private String titleDetail;

    @ApiModelProperty("")
    private String titleDetailAboutId;

    @ApiModelProperty("")
    private String componentsId;

    @ApiModelProperty("组件名称")
    private String componentsName;

    @ApiModelProperty("组件类型（1：标准组件，0：非标组件）")
    private String componentsType;

    @ApiModelProperty("")
    private String componentsResultList;

    @ApiModelProperty("检查类型 10:只能为数字 20:电话 30:邮箱 40:金额,最多保留两位小数")
    private String checkType;

    @ApiModelProperty("是否可以为空 1:可以为空 0:不可为空")
    private String isNull;

    @ApiModelProperty("字段长度")
    private String length;

    @ApiModelProperty("对齐方式(1:左对齐 2:居中 3:右对齐)")
    private String alignmentMode;

    @ApiModelProperty("")
    private String addHiddenFlg;

    @ApiModelProperty("")
    private String addFlg;

    @ApiModelProperty("是否可以编辑")
    private String editFlg;

    @ApiModelProperty("是否为系统字段 1：是 0：否")
    private String sysFlg;

    @ApiModelProperty("快速检索API")
    private String searchApi;

    @ApiModelProperty("初始化值")
    private String initValue;

    @ApiModelProperty("初始化值调用的api")
    private String initValueApi;

    @ApiModelProperty("列表点击弹出组件类型，1：标准组件，2：跳转页面；3：非标准组件")
    private String rowComponentsType;

    @ApiModelProperty("组件id或者页面url(url中一定要带urlPageName值为页面标签名称)")
    private String rowComponentsId;

    @ApiModelProperty("组件标题或者页面标签名称")
    private String rowComponentsName;

    @ApiModelProperty("当前取值的属性名称")
    private String rowTitleDetailAboutId;

    @ApiModelProperty("目标获取数据的字段名称")
    private String rowComponentsResultList;


}