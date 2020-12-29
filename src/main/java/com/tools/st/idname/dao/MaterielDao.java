package com.tools.st.idname.dao;

import com.tools.st.idname.model.MoldMateriel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaterielDao extends IDNameDao<Long, MoldMateriel> {
    @Select("select id, materiel_code as materielCode, materiel_name as materielName, bom_code as bomCode, drawing_number as drawingNumber, " +
            "specification from base_materiel where id=#{id}")
    MoldMateriel selectById(Long id);

    @Select("select id, materiel_code as materielCode, materiel_name as materielName, bom_code as bomCode, drawing_number as drawingNumber, " +
            "specification from base_materiel where id in (${ids})")
    List<MoldMateriel> selectByIds(@Param("ids") String ids);
}
