package com.tools.st.idname.dao;

import com.tools.st.idname.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao extends IDNameDao<Long, SysUser> {

    @Select("select user_id as id, username as username, name from sys_user where user_id=#{id}")
    SysUser selectById(Long id);

    @Select("select user_id as id, username as username, name from sys_user where user_id in (${ids})")
    List<SysUser> selectByIds(@Param("ids") String ids);
}
