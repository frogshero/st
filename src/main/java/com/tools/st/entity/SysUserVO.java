package com.tools.st.entity;

import com.tools.st.entity.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true, includeFieldNames = true) 
@EqualsAndHashCode(callSuper=false)
@Data
public class SysUserVO extends BaseVO {

	private Long userId; //ID
	private Long deptId; //部门ID
	private String name; //姓名
	private String username; //用户名
}
