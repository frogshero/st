package com.tools.st.idname.service;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.tools.st.idname.enums.DataTableEnum;
import com.tools.st.idname.model.IdNameEntityBase;
import com.tools.st.idname.model.SetIdNameInfo;
import com.tools.st.idname.repo.IdNameConfiguration;
import com.tools.st.idname.repo.IdNameRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class IdNameService {

    @Autowired
    private IdNameRepo[] injectedRepos;

    private List<IdNameRepo> idNameRepos;

    private LoadingCache<Class, List<SetIdNameInfo>> methodCache = null;

    @Autowired
    private IdNameConfiguration config;

    @PostConstruct
    public void init() {
        methodCache = CacheBuilder
                .newBuilder()
                .maximumSize(50)
                .build(new CacheLoader<Class, List<SetIdNameInfo>>() {
                           @Override
                           public List<SetIdNameInfo> load(Class key) {
                               return extractMethods(key);
                           }
                       }
                );

        idNameRepos = Stream.of(injectedRepos).sorted((e1,e2) -> orderRepo(e1, e2)).collect(Collectors.toList());
    }

    public void clearCache() {
        log.info("clear all cache");
        idNameRepos.stream().forEach(IdNameRepo::clearCache);
    }

    private int orderRepo(IdNameRepo e1, IdNameRepo e2) {
        return e1.getType().ordinal() - e2.getType().ordinal();
    }

    private void loadByIds(Map<DataTableEnum, Set<Object>> idsMap) {
        for (Map.Entry<DataTableEnum, Set<Object>> entry : idsMap.entrySet()) {
            IdNameRepo repo = idNameRepos.get(entry.getKey().ordinal());
            repo.loadObjectByIds(entry.getValue());
        }
    }

    private Map<DataTableEnum, Set<Object>> getSelectIds(List list, List<SetIdNameInfo> setIdNameInfos) throws InvocationTargetException, IllegalAccessException {
        Map<DataTableEnum, Set<Object>> result = new HashMap<>();
        if (list.size() == 0) return result;

        MethodAccess methodAccess = MethodAccess.get(list.get(0).getClass());
        for (Object o : list) {
            for (SetIdNameInfo setIdNameInfo : setIdNameInfos) {
                Object idObj = exeGet(methodAccess, o, setIdNameInfo.getIdMethod());
                if (idObj == null) continue;

                //缓存没有找到？
                IdNameRepo repo = idNameRepos.get(setIdNameInfo.getTableEnum().ordinal());
                IdNameEntityBase cached = repo.getPresentCacheObj(setIdNameInfo.getKeyPrefix(), idObj);
                if  (cached == null) {
                    Set<Object> ids = result.get(setIdNameInfo.getTableEnum());
                    if (ids == null) {
                        ids = new HashSet<>();
                        result.put(setIdNameInfo.getTableEnum(), ids);
                    }
                    if (StringUtils.isNotBlank(setIdNameInfo.getKeyPrefix())) {
                        //按type查询就可以
                        ids.add(setIdNameInfo.getKeyPrefix());
                    } else {
                        ids.add(idObj);
                    }
                }
            }
        }
        return result;
    }
//
    public IdNameEntityBase getCacheUser(Long userId) throws ExecutionException {
        IdNameRepo repo = idNameRepos.get(DataTableEnum.user.ordinal());
        return repo.getCacheObj("", userId);
    }

    public IdNameEntityBase getCacheDept(Long deptId) throws ExecutionException {
        IdNameRepo repo = idNameRepos.get(DataTableEnum.dept.ordinal());
        return repo.getCacheObj("", deptId);
    }

    public IdNameEntityBase getCacheBusinessDict(Long businessDicId) throws ExecutionException {
        IdNameRepo repo = idNameRepos.get(DataTableEnum.businessDic.ordinal());
        return repo.getCacheObj("", businessDicId);
    }

    public IdNameEntityBase getSysDict(Long sysDicId) throws ExecutionException {
        IdNameRepo repo = idNameRepos.get(DataTableEnum.sysDic.ordinal());
        return repo.getCacheObj("", sysDicId);
    }

    private Object exeGet(MethodAccess method, Object o, String methodName) {
        try {
            return method.invoke(o, methodName);
        } catch(Exception e) {
            log.error("执行错误：{} {}", o.getClass().getName(), methodName);
            throw e;
        }
    }

    private void exeSet(MethodAccess method, Object o, String methodName, Object val) {
        try {
            method.invoke(o, methodName, val);
        } catch(Exception e) {
            log.error("执行错误：{} {}", o.getClass().getName(), methodName);
            throw e;
        }
    }

    public void setListName(List list) {
        if (list.size() == 0) return;
        Class clz = list.get(0).getClass();
        try {
            List<SetIdNameInfo> setIdNameInfos = methodCache.get(clz);
            if (setIdNameInfos.size() == 0) {
                throw new RuntimeException("没有设置IDName注解");
            }

            if (config.isBatchSelect()) {
                Map<DataTableEnum, Set<Object>> idsMap = getSelectIds(list, setIdNameInfos);
                loadByIds(idsMap);
            }

            for (Object o : list) {
                MethodAccess targetMethod = MethodAccess.get(o.getClass());
                for (SetIdNameInfo info : setIdNameInfos) {
                    Object idObj = exeGet(targetMethod, o, info.getIdMethod());
                    if (idObj == null) continue;

                    IdNameRepo repo = idNameRepos.get(info.getTableEnum().ordinal());
                    IdNameEntityBase src = repo.getCacheObj(info.getKeyPrefix(), idObj);
                    if (src.getId().equals(IdNameRepo.NULL_ID)) {
                        continue;
                    }

                    MethodAccess srcMethod = MethodAccess.get(src.getClass());
                    if (src == null) {
                        log.warn("没有找到对应的实体 {} {}", idObj, info.getTableEnum());
                        continue;
                    }
                    exeSet(targetMethod, o, info.getSetMethod(),
                            exeGet(srcMethod, src, info.getSrcMethod()));
                }
            }
        } catch (Exception e) {
            log.error("设置名称失败：{} {}", clz.getName(), e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("设置名称失败");
        }
    }



    private String computDefaultIdFieldName(String fldName) {
        //要么name结尾，要么code结尾
        //updated_by, created_by不加ID
        if (fldName.endsWith("Name")) {
            return StringUtils.removeEndIgnoreCase(fldName, "Name")
                    + (fldName.equals("updatedByName") || fldName.equals("createdByName") ? "" : "Id");
        } else if (fldName.endsWith("Code")) {
            return StringUtils.removeEndIgnoreCase(fldName, "Code")
                    + (fldName.equals("updatedByCode") || fldName.equals("createdByCode") ? "" : "Id");
        } else {
            throw new RuntimeException(fldName + "必须指定来源ID字段");
        }
    }

    public List<SetIdNameInfo> extractMethods(Class clz) {
        Field[] flds = clz.getDeclaredFields();
        List<SetIdNameInfo> methods = Lists.newArrayList();

        for(Field fld : flds) {
            try {
                SetIdName[] setIdNames = fld.getAnnotationsByType(SetIdName.class);
                if (setIdNames != null && setIdNames.length > 0) {
                    SetIdName setIdName = setIdNames[0];
                    String fromField = setIdName.idField();
                    if (StringUtils.isBlank(fromField)) {
                        fromField = computDefaultIdFieldName(fld.getName());
                    }
                    String idField = "get" + StringUtils.capitalize(fromField);
                    String setField = "set" + StringUtils.capitalize(fld.getName());
                    String srcField = "get" + StringUtils.capitalize(setIdName.nameField());

                    methods.add(new SetIdNameInfo(idField, setField, srcField, setIdName.type(), setIdName.keyPrefix(), setIdName.order()));
                }
            } catch (Exception e) {
                log.error("获取字段信息失败：{} {}", fld.getName(), e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("获取字段信息失败");
            }
        }
        //处理先后顺序
        methods.sort(Comparator.comparingInt(SetIdNameInfo::getOrder));
        return methods;
    }

}
