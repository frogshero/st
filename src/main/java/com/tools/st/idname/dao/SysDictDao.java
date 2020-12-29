package com.tools.st.idname.dao;

import com.tools.st.idname.model.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysDictDao extends IDNameDao<Long, SysDict> {

    @Select("select name, value as id, type from sys_dict where type=#{type} and value =#{code} and enableflg=1")
    SysDict selectById(Long id);

    @Select("select name, value as id, type from sys_dict where type in (${ids})")
    List<SysDict> selectByIds(@Param("ids") String ids);
}
