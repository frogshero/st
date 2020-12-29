package com.tools.st.idname.dao;

import com.tools.st.idname.model.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptDao extends IDNameDao<Long, Dept> {
    @Select("select dept_id as id, name, department_code as departmentCode from sys_dept where dept_id=#{id}")
    Dept selectById(Long id);

    @Select("select dept_id as id, name, department_code as departmentCode from sys_dept where dept_id in (${ids})")
    List<Dept> selectByIds(@Param("ids") String ids);
}
