package com.tools.st.idname.repo;

import com.tools.st.idname.enums.DataTableEnum;
import com.tools.st.idname.model.IdNameEntityBase;

import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface IdNameRepo<K, T extends IdNameEntityBase> {

    Long NULL_ID = -9999L;

    T getCacheObj(String keyPrefix, K id) throws ExecutionException;

    T getPresentCacheObj(String keyPrefix, K id);

    DataTableEnum getType();

    /**
     * 为性能计
     */
    void loadObjectByIds(Set<K> ids);

    void clearCache();
}
