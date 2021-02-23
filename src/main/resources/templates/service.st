service(param) ::= <<
package $param.servicePackage$;

import com.google.common.collect.Lists;
import com.ymc.mes.basic.common.service.SysCodeService;
import com.ymc.mes.basic.system.model.CmmSysUserVO;
import com.ymc.mes.basic.system.model.SysComTableConfigVO;
import com.ymc.mes.basic.system.service.SysComTableConfigService;
import com.ymc.mes.basic.system.service.SysUserService;
import com.ymc.mes.mold.warehouse.common.AbnormalOperationEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import $param.daoClzFullName$;
import $param.voClzFullName$;

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

    private String ERROR_TEMPLATE = "$param.entityChinese$【%d】%s失败, 原因：%s";
    private String SUCCESS_TEMPLATE = "$param.entityChinese$【%d】%s成功";

    private CmmSysUserVO getUser() {
         return sysUserService.getUser();
    }

    /**
     * 查询列表
     * @return
     */
    public Map<String, Object> findList($param.voClzName$ $param.voVariable$) {
        if ($param.voVariable$.getComId() == null) {
            $param.voVariable$.setComId(getUser().getComId());
        }

        Map<String, Object> returnMap = new HashMap<>();
        // 获取mold列表
        List<$param.voClzName$> moldList = $param.daoVariable$.findList($param.voVariable$);
        // 获取mold表单配置信息
        List<SysComTableConfigVO> titleList = sysComTableConfigService.getTableConfig($param.voVariable$.getComId(), "$param.javaName$");
        // 表格内容
        returnMap.put("tableList", moldList);
        // 表头信息
        returnMap.put("titleList", titleList);
        return returnMap;
    }

    /**
     * 插入
     * @param $param.voVariable$
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert($param.voClzName$ $param.voVariable$) {
        if ($param.voVariable$.get$param.noField$() == null) {
            String no = sysCodeService.findCode("$param.javaName$", getUser().getComId());
            $param.voVariable$.set$param.noField$(no);
        }
        setBaseField($param.voVariable$, AbnormalOperationEnum.insert);
        $param.daoVariable$.insert($param.voVariable$);
    }

    /**
     * 更新
     * @param $param.voVariable$
     */
    @Transactional(rollbackFor = Exception.class)
    public void update($param.voClzName$ $param.voVariable$) {
        setBaseField($param.voVariable$, AbnormalOperationEnum.update);
        $param.daoVariable$.updateByPrimaryKey($param.voVariable$);
    }

    private void setBaseField($param.voClzName$ $param.voVariable$, AbnormalOperationEnum operationEnum) {
        CmmSysUserVO user = getUser();
        if (operationEnum == AbnormalOperationEnum.insert) {
            $param.voVariable$.setEnableflg(1);
            $param.voVariable$.setCreatedBy(user.getUserId());
            $param.voVariable$.setCreatedTime(new Date());
        }
        if (operationEnum == AbnormalOperationEnum.delete) {
            $param.voVariable$.setEnableflg(0);
        }
        $param.voVariable$.setUpdatedBy(user.getUserId());
        $param.voVariable$.setUpdatedTime(new Date());
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
            $param.voClzName$ voForDel = $param.daoVariable$.selectByPrimaryKey(e);
            if (voForDel == null) {
                feedback.add("ID不正确：" + e);
                return;
            }

            setBaseField(voForDel, AbnormalOperationEnum.delete);
            $param.daoVariable$.updateByPrimaryKey(voForDel);
        });
        return feedback;
    }

    private void setChecked($param.voClzName$ voForCheck, boolean checked) {
        if (checked) {
            //审核人
            voForCheck.setReviewUserId(getUser().getUserId());
            voForCheck.setReviewTime(new Date());
        } else {
            voForCheck.setReviewUserId(null);
            voForCheck.setReviewTime(null);
        }
        setBaseField(voForCheck, AbnormalOperationEnum.update);
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
            $param.voClzName$ voForCheck = $param.daoVariable$.selectByPrimaryKey(e);
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
}
>>