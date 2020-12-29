package com.tools.st.idname.dao;

import com.tools.st.idname.model.BusinessDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BusinessDictDao extends IDNameDao<Long, BusinessDict> {
    @Select("select id, serial_number as serialNumber, name from sys_base_business_data_dict where id=#{id}")
    BusinessDict selectById(Long id);

    @Select("select id, serial_number as serialNumber, name from sys_base_business_data_dict where id in (${ids})")
    List<BusinessDict> selectByIds(@Param("ids") String ids);
}
