package com.tools.st.idname.dao;

import com.tools.st.idname.model.IdNameEntityBase;

import java.util.List;

public interface IDNameDao<K, T extends IdNameEntityBase> {

    T selectById(K id);

    T  selectByCode(String type, K code);

    List<T> selectByIds(String ids);
}
