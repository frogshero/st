package com.tools.st.idname.dao;

import com.tools.st.idname.model.MoldOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MoldOrderDao extends IDNameDao<Long, MoldOrder> {
    @Select("select id, made_no as madeNo, made_type_id as madeTypeId from mold_order where id=#{id}")
    MoldOrder selectById(Long id);

    @Select("select id, made_no as madeNo, made_type_id as madeTypeId from mold_order where id in (${ids})")
    List<MoldOrder> selectByIds(@Param("ids") String ids);
}
