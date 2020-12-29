package com.tools.st.idname.dao;

import com.tools.st.idname.model.MoldProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProcessDao extends IDNameDao<Long, MoldProcess> {
    @Select("select id, serial_code as serialCode, process_name as processName from mold_craft_process where id=#{id}")
    MoldProcess selectById(Long id);

    @Select("select id, serial_code as serialCode, process_name as processName from mold_craft_process where id in (${ids})")
    List<MoldProcess> selectByIds(@Param("ids") String ids);
}
