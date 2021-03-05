service(param) ::= <<
package $param.servicePackage$;

import com.google.common.collect.Lists;
import com.ymc.mes.basic.common.service.SysCodeService;
import com.ymc.mes.basic.system.model.CmmSysUserVO;
import com.ymc.mes.basic.system.model.SysComTableConfigVO;
import com.ymc.mes.basic.system.service.SysComTableConfigService;
import com.ymc.mes.basic.system.service.SysUserService;
import com.ymc.mes.basic.common.model.OperationEnum;
import com.ymc.mes.mold.export.ExportFileParam;
import com.ymc.mes.mold.export.ExportFileService;
import com.ymc.mes.basic.common.model.FileVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.ymc.mes.basic.common.model.CommonBatchRequest;
import $param.modelPackage$.$param.javaName$;
import $param.daoClzFullName$;
import $param.voClzFullName$;
import $param.modelPackage$.$param.listRequestClz$;
import $param.modelPackage$.$param.entityRequestClz$;

@Service
public class $param.serviceClzName$ {

    @Autowired
    private $param.daoClzName$ $param.daoVariable$;
    @Autowired
    private SysComTableConfigService sysComTableConfigService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysCodeService sysCodeService;
    @Autowired
    ExportFileService exportFileService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private String OP_ERROR_TEMPLATE = "$param.entityChinese$【%s】%s失败, 原因：%s";
    private String OP_SUCCESS_TEMPLATE = "$param.entityChinese$【%s】%s成功";

    private CmmSysUserVO getUser() {
         return sysUserService.getUser();
    }

    /**
     * 查询列表
     * @return
     */
    public Map<String, Object> findList($param.listRequestClz$ request, String tableId) {
        Map<String, Object> returnMap = new HashMap<>();
        List<$param.voClzName$> moldList = $param.daoVariable$.findList(request);
        List<SysComTableConfigVO> titleList = sysComTableConfigService.getTableConfig(getUser().getComId(),
            StringUtils.isBlank(tableId) ? "$param.lJavaName$" : tableId);
        returnMap.put("tableList", moldList);
        returnMap.put("titleList", titleList);
        return returnMap;
    }

    private Long doInsert($param.entityRequestClz$ request) {
        $param.javaName$ $param.lJavaName$ = new $param.javaName$();
        BeanUtils.copyProperties(request, $param.lJavaName$);

//      if (request.getReviewUserId() == null) {
//                CommonException.tipMessage(String.format(OP_ERROR_TEMPLATE, $param.lJavaName$.get$param.noField$(), "保存失败", "reviewUsreId不能为空"));
//      }
        if ($param.lJavaName$.get$param.noField$() == null) {
            String no = sysCodeService.findCode("$param.lJavaName$", getUser().getComId());
            $param.lJavaName$.set$param.noField$(no);
        }
        setBaseField($param.lJavaName$, OperationEnum.insert);
        $param.daoVariable$.insert($param.lJavaName$);
        return $param.lJavaName$.getId();
    }

    /**
     * 插入
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    public Long insert($param.entityRequestClz$ request) {
        return doInsert(request);
    }

    private void doUpdate($param.entityRequestClz$ request) {
        Assert.notNull(request.getId(), "id不能为空");
        $param.javaName$ entityInDB = $param.daoVariable$.selectByPrimaryKey(request.getId());
        Assert.notNull(entityInDB, "id不正确");
        if (entityInDB.getReviewUserId() != null) {
            throw CommonException.tipMessage(String.format(OP_ERROR_TEMPLATE, entityInDB.get$param.noField$(), "修改", "已经审核"));
        }

        $param.javaName$ $param.lJavaName$ = new $param.javaName$();
        BeanUtils.copyProperties(entityInDB, $param.lJavaName$);
        BeanUtils.copyProperties(request, $param.lJavaName$);
        setBaseField($param.lJavaName$, OperationEnum.update);
        $param.daoVariable$.updateByPrimaryKey($param.lJavaName$);
    }

    /**
     * 更新
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    public void update($param.entityRequestClz$ request) {
        doUpdate(request);
    }

    private void setBaseField($param.javaName$ $param.lJavaName$, OperationEnum operationEnum) {
        serviceEntityUtil.setAuditFields(moldWarehouseEntryVO, operationEnum);
    }

    /**
     * 批量删除
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<String> deleteTableByIds(List<Long> ids) {
        List<String> feedback = Lists.newArrayList();
        for (Long id : ids) {
            $param.javaName$ voForDel = $param.daoVariable$.selectByPrimaryKey(id);
            Assert.notNull(voForDel, "ID不正确");
            if (voForDel.getReviewUserId() != null) {
                feedback.add(String.format(OP_ERROR_TEMPLATE, voForDel.get$param.noField$(), "删除", "已经审核"));
                continue;
            }
            setBaseField(voForDel, OperationEnum.delete);
            $param.daoVariable$.updateByPrimaryKey(voForDel);
        }
        return feedback;
    }

    private void setEntityChecked($param.javaName$ voForCheck, boolean checked) {
        serviceEntityUtil.setVerifiyFields(voForCheck, checked);
        setBaseField(voForCheck, OperationEnum.update);
        $param.daoVariable$.updateByPrimaryKey(voForCheck);
    }

    /**
     * 审核，撤审
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<String> checkOrUncheck(List<Long> ids, boolean audit) {
        List<String> msgList = Lists.newArrayList();
        for (Long id : ids) {
            $param.javaName$ voForCheck = $param.daoVariable$.selectByPrimaryKey(id);
            Assert.notNull(voForCheck, "ID不正确：" + id);

            boolean checked = voForCheck.getReviewUserId() != null;
            if (audit && checked) {
                msgList.add(String.format(OP_ERROR_TEMPLATE, voForCheck.get$param.noField$(), "审核", "已经是审核状态"));
                continue;
            } else if (!audit & !checked) {
                msgList.add(String.format(OP_ERROR_TEMPLATE, voForCheck.get$param.noField$(), "撤审", "不是审核状态"));
                continue;
            }
            setEntityChecked(voForCheck, audit);
        }
        return msgList;
    }

    public FileVo exportExcel($param.listRequestClz$ request) {
        List<$param.voClzName$> dataList = $param.daoVariable$.findList(request);
        if (dataList.size() == 0) {
            return new FileVo("", "");
        }
        ExportFileParam exportFileParam = new ExportFileParam("$param.lJavaName$", "$param.entityChinese$", dataList);
        return exportFileService.exportByModel(exportFileParam);
    }

    private void batchInsert(List<$param.javaName$> itemList) {
        //ExecutorType.BATCH
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        $param.javaName$Dao mapper = session.getMapper($param.javaName$Dao.class);
        for (int i = 0; i < itemList.size(); i++) {
            mapper.insert(itemList.get(i));
            if (i % 500 == 499) {
                session.commit();
                session.clearCache();
            }
        }
        session.commit();
        session.clearCache();
    }
}
>>