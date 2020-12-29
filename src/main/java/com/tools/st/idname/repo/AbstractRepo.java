package com.tools.st.idname.repo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.tools.st.idname.dao.BusinessDictDao;
import com.tools.st.idname.dao.IDNameDao;
import com.tools.st.idname.enums.DataTableEnum;
import com.tools.st.idname.model.IdNameEntityBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public abstract class AbstractRepo<K, T extends IdNameEntityBase> implements IdNameRepo<K, T>, ApplicationContextAware {
    @Autowired
    BusinessDictDao mapper;

    protected LoadingCache<K, T> objCache = null;

    private static String DELIMITER = "@@";
    private static String CODE_FORMAT = "%s" + DELIMITER + "%s";

    private int maxSize;
    private int expireDuration;
    private TimeUnit expireUnit;
    private DataTableEnum tableEnum;
    private ApplicationContext context;
    private IDNameDao<K, T> idNameDao;

    public AbstractRepo(DataTableEnum tableEnum, int maxSize, int expireDuration, TimeUnit expireUnit) {
        this.tableEnum = tableEnum;
        this.maxSize = maxSize;
        this.expireDuration = expireDuration;
        this.expireUnit = expireUnit;
    }

    public T loadByType(K id) {
        String[] keyArr = ((String)id).split(DELIMITER);
        if (keyArr.length != 2) {
            return null;
        }
        return idNameDao.selectByCode(keyArr[0], (K)keyArr[1]);
    }

    public T loadObject(K id) {
        if (tableEnum.isQueryByType()) {
            return loadByType(id);
        } else {
            return idNameDao.selectById(id);
        }
    }

    public List<T> loadByIds(String ids) {
        return idNameDao.selectByIds(ids);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public DataTableEnum getType() {
        return this.tableEnum;
    }

    @PostConstruct
    public void init() {
        idNameDao = (IDNameDao)context.getBean(tableEnum.getMapperClass());

        objCache = CacheBuilder
                .newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(expireDuration, expireUnit)
                .build(new CacheLoader<K, T>() {
                    @Override
                    public T load(K key) {
                        T cached = loadObject(key);
                        if (cached == null) {
                            IdNameEntityBase base = new IdNameEntityBase();
                            base.setId(NULL_ID);
                            return (T)base;
                        } else {
                            return cached;
                        }
                    }
                });
    }

    @Override
    public T getCacheObj(String keyPrefix, K id) throws ExecutionException {
        if (tableEnum.isQueryByType()) {
            return objCache.getIfPresent((K)String.format(CODE_FORMAT, keyPrefix, id));
        } else {
            return objCache.get(id);
        }
    }

    @Override
    public T getPresentCacheObj(String keyPrefix, K id) {
        if (tableEnum.isQueryByType()) {
            return objCache.getIfPresent((K)String.format(CODE_FORMAT, keyPrefix, id));
        } else {
            return objCache.getIfPresent(id);
        }
    }

    @Override
    public void loadObjectByIds(Set<K> ids) {
        //???
        //assert(ids.size() < 2000);
        if (tableEnum.isQueryByType()) {
            List<T> entities = loadByIds("'" + StringUtils.join(ids, "','") + "'");
            if (entities != null) {
                entities.stream().forEach(e -> objCache.put((K)String.format(CODE_FORMAT, e.getType(), e.getId()), e));
            }
        } else {
            List<T> entities = loadByIds(StringUtils.join(ids, ','));
            if (entities != null) {
                entities.stream().forEach(e -> objCache.put((K) e.getId(), e));
            }
        }
    }

    @Override
    public void clearCache() {
        objCache.invalidateAll();
    }
}
