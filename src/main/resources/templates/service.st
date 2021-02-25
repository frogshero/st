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

    private String ERROR_TEMPLATE = "$param.entityChinese$【%s】%s失败, 原因：%s";
    private String SUCCESS_TEMPLATE = "$param.entityChinese$【%s】%s成功";

    private CmmSysUserVO getUser() {
         return sysUserService.getUser();
    }

    /**
     * 查询列表
     * @return
     */
    public Map<String, Object> findList($param.listRequestClz$ request) {
        Map<String, Object> returnMap = new HashMap<>();
        List<$param.voClzName$> moldList = $param.daoVariable$.findList(request);
        List<SysComTableConfigVO> titleList = sysComTableConfigService.getTableConfig(getUser().getComId(), "$param.lJavaName$");
        returnMap.put("tableList", moldList);
        returnMap.put("titleList", titleList);
        return returnMap;
    }

    /**
     * 插入
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert($param.entityRequestClz$ request) {
        $param.javaName$ $param.lJavaName$ = new $param.javaName$();
        BeanUtils.copyProperties(request, $param.lJavaName$);

        if ($param.lJavaName$.get$param.noField$() == null) {
            String no = sysCodeService.findCode("$param.lJavaName$", getUser().getComId());
            $param.lJavaName$.set$param.noField$(no);
        }
        setBaseField($param.lJavaName$, OperationEnum.insert);
        $param.daoVariable$.insert($param.lJavaName$);
    }

    /**
     * 更新
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    public void update($param.entityRequestClz$ request) {
        Assert.notNull(request.getId(), "id不能为空");
        $param.javaName$ $param.lJavaName$ = $param.daoVariable$.selectByPrimaryKey(request.getId());
        Assert.notNull($param.lJavaName$, "id不正确");
        BeanUtils.copyProperties(request, $param.lJavaName$);

        setBaseField($param.lJavaName$, OperationEnum.update);
        $param.daoVariable$.updateByPrimaryKey($param.lJavaName$);
    }

    private void setBaseField($param.javaName$ $param.lJavaName$, OperationEnum operationEnum) {
        //enableflg,comId,createdBy,createdTime,updatedBy,updatedTime
        CmmSysUserVO user = getUser();
        if (operationEnum == OperationEnum.insert) {
            $param.lJavaName$.setEnableflg(1);
            $param.lJavaName$.setCreatedBy(user.getUserId());
            $param.lJavaName$.setCreatedTime(new Date());
        }
        if (operationEnum == OperationEnum.delete) {
            $param.lJavaName$.setEnableflg(0);
        }
        if ($param.lJavaName$.getComId() == null) {
            $param.lJavaName$.setComId(user.getComId());
        }
        $param.lJavaName$.setUpdatedBy(user.getUserId());
        $param.lJavaName$.setUpdatedTime(new Date());
    }

    private void operateByIds(String idListStr, Consumer<Long> operation) {
        if (StringUtils.isBlank(idListStr)) return;
        String[] idArr = idListStr.split(",");
        for (String idStr : idArr) {
            if (StringUtils.isBlank(idStr)) continue;
            Assert.isTrue(StringUtils.isNumeric(idStr), "ID不正确：" + idStr);

            operation.accept(Long.valueOf(idStr));
        }
    }

    /**
     * 批量删除
     * @param idListStr
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<String> deleteTableByIds(String idListStr) {
        List<String> feedback = Lists.newArrayList();
        operateByIds(idListStr, e -> {
            $param.javaName$ voForDel = $param.daoVariable$.selectByPrimaryKey(e);
            if (voForDel == null) {
                feedback.add("ID不正确：" + e);
                return;
            }

            setBaseField(voForDel, OperationEnum.delete);
            $param.daoVariable$.updateByPrimaryKey(voForDel);
        });
        return feedback;
    }

    private void setChecked($param.javaName$ voForCheck, boolean checked) {
        if (checked) {
            //审核人
            voForCheck.setReviewUserId(getUser().getUserId());
            voForCheck.setReviewTime(new Date());
        } else {
            voForCheck.setReviewUserId(null);
            voForCheck.setReviewTime(null);
        }
        setBaseField(voForCheck, OperationEnum.update);
        $param.daoVariable$.updateByPrimaryKey(voForCheck);
    }

    /**
     * 审核，撤审
     * @param commaIds
     * @param audit
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<String> checkOrUncheck(String commaIds, boolean audit) {
        List<String> msgList = Lists.newArrayList();
        operateByIds(commaIds, e -> {
            $param.javaName$ voForCheck = $param.daoVariable$.selectByPrimaryKey(e);
            Assert.notNull(voForCheck, "ID不正确：" + e);

            boolean checked = voForCheck.getReviewUserId() != null;
            if (audit && checked) {
                msgList.add(String.format(ERROR_TEMPLATE, voForCheck.get$param.noField$(), "审核", "已经是审核状态"));
                return;
            } else if (!audit & !checked) {
                msgList.add(String.format(ERROR_TEMPLATE, voForCheck.get$param.noField$(), "撤审", "不是审核状态"));
                return;
            }
            setChecked(voForCheck, audit);
            msgList.add(String.format(SUCCESS_TEMPLATE, voForCheck.get$param.noField$(), audit ? "审核" : "撤审"));
        });
        return msgList;
    }

    public FileVo exportExcel($param.listRequestClz$ request) {
        List<$param.voClzName$> dataList = $param.daoVariable$.findList(request);
        ExportFileParam exportFileParam = new ExportFileParam("$param.lJavaName$", "$param.entityChinese$", dataList);
        return exportFileService.exportByModel(exportFileParam);
    }
}
>>