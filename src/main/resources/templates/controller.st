controller(param) ::= <<
package $param.controllerPackage$;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ymc.mes.basic.common.BaseController;
import com.ymc.mes.basic.common.Result;
import $param.voClzFullName$;
import $param.serviceClzFullName$;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.Nullable;
import java.util.List;

import com.ymc.mes.basic.common.model.CommonBatchRequest;
import $param.modelPackage$.$param.javaName$;
import $param.modelPackage$.$param.listRequestClz$;
import $param.modelPackage$.$param.entityRequestClz$;
import com.ymc.mes.basic.common.model.FileVo;
import javax.validation.Valid;


@RestController
@RequestMapping("$param.requestMapping$")
public class $param.controllerClzName$ extends BaseController {

    @Autowired
    private $param.serviceClzName$ $param.serviceVariable$;

    @ApiOperation("查询$param.entityChinese$列表")
    @GetMapping("/findList")
    public Result<List<$param.voClzName$\>> findList(@ApiParam("过滤参数") $param.listRequestClz$ request,
                                   @ApiParam(value = "tableId", required = false) @Nullable @RequestParam("tableId") String tableId,
                                   @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGESIZE) Integer pageSize,
                                   @RequestParam(value = "pageNum", defaultValue = DEFAULT_PAGENUM) Integer pageNum) {
        Page<?> page = PageHelper.startPage(pageNum, pageSize, true);
        return successResult($param.serviceVariable$.findList(request, tableId), page.getTotal());
    }

    @ApiOperation("查询单个$param.entityChinese$")
    @GetMapping("/find/{id}")
    public Result<MoldBomMaterialCost> findOne(@PathVariable Long id) {
        return successResult($param.serviceVariable$.findOne(id));
    }

    @ApiOperation("新增$param.entityChinese$")
    @PostMapping("/insert")
    public Result<Long> insert(@RequestBody @Valid $param.entityRequestClz$ request) {
        return successResult($param.serviceVariable$.insert(request));
    }

    @ApiOperation("更新$param.entityChinese$")
    @PutMapping("/update")
    public Result update(@RequestBody @Valid $param.entityRequestClz$ request) {
        $param.serviceVariable$.update(request);
        return successResult(null);
    }

    @ApiOperation("删除$param.entityChinese$")
    @PostMapping("/delete")
    public Result<List<String\>> delete(@RequestBody CommonBatchRequest commonBatchRequest) {
        List<String> feedback = $param.serviceVariable$.deleteTableByIds(commonBatchRequest.getIds());
        if (feedback.size() > 0) {
            return new Result(ERROR, "部分操作未能成功", feedback);
        } else {
            return successResult(null);
        }
    }

    @ApiOperation("审核$param.entityChinese$")
    @PostMapping("/review")
    public Result<List<String\>> review(@RequestBody CommonBatchRequest commonBatchRequest) {
        return auditOrCancelAudit(commonBatchRequest, true);
    }

    private Result<List<String\>> auditOrCancelAudit(CommonBatchRequest commonBatchRequest, boolean audit) {
        List<String> msg = $param.serviceVariable$.checkOrUncheck(commonBatchRequest.getIds(), audit);
        if (msg.size() == 0) {
            return successResult(null);
        } else {
            return new Result(ERROR, "部分操作未能成功", msg);
        }
    }

    @ApiOperation("撤审$param.entityChinese$")
    @PostMapping("/cancelReview")
    public Result<List<String\>> cancelReview(@RequestBody CommonBatchRequest commonBatchRequest) {
        return auditOrCancelAudit(commonBatchRequest, false);
    }

    @ApiOperation("$param.entityChinese$导出Excel")
    @GetMapping("/exportExcel")
    public Result<FileVo> exportExcel(@ApiParam $param.listRequestClz$ request) {
        return successResult($param.serviceVariable$.exportExcel(request));
    }
}
>>