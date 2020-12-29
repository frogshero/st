package com.tools.st.mapper;


import com.tools.st.entity.QcAbnormalTable;
import com.tools.st.entity.QcAbnormalTableVO;
import com.tools.st.mapper.base.GenericMapper;

import java.util.List;

public interface QcAbnormalTableDao extends GenericMapper<QcAbnormalTable, Long> {
    List<QcAbnormalTableVO> findList(QcAbnormalTableVO param);

    QcAbnormalTable findByQcNo(Long qcNo);

    List<QcAbnormalTableVO> findKanban();
}