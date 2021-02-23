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

import java.util.List;

@RestController
@RequestMapping("$param.requestMapping$")
public class $param.controllerClzName$ extends BaseController {

    @Autowired
    private $param.serviceClzName$ $param.serviceVariable$;

    @ApiOperation("查询$param.entityChinese$列表")
    @GetMapping("/findList")
    public Result findList(@ApiParam("过滤参数") $param.voClzName$ queryParam,
                                   @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGESIZE) Integer pageSize,
                                   @RequestParam(value = "pageNum", defaultValue = DEFAULT_PAGENUM) Integer pageNum) {
        Page<?> page = PageHelper.startPage(pageNum, pageSize, true);
        return successResult($param.serviceVariable$.findList(queryParam), page.getTotal());
    }

    @ApiOperation("新增$param.entityChinese$")
    @PostMapping("/insert")
    public Result insert(@RequestBody $param.voClzName$ $param.voVariable$) {
        $param.serviceVariable$.insert($param.voVariable$);
        return successResult(null);
    }

    @ApiOperation("更新$param.entityChinese$")
    @PostMapping("/update")
    public Result update(@RequestBody $param.voClzName$ $param.voVariable$) {
        $param.serviceVariable$.update($param.voVariable$);
        return successResult(null);
    }

    @ApiOperation("删除$param.entityChinese$")
    @PostMapping("/delete")
    public Result deleteAbnormalTable(@RequestBody $param.voClzName$ $param.voVariable$) {
        List<String> feedback = $param.serviceVariable$.deleteTableByIds($param.voVariable$.getIds());
        if (feedback.size() > 0) {
            return new Result(RT_ERROR_DELETE, "删除失败", feedback);
        } else {
            return successResult(null);
        }
    }

    @ApiOperation("审核$param.entityChinese$")
    @PostMapping("/review")
    public Result review(@RequestBody $param.voClzName$ $param.voVariable$) {
        return auditOrCancelAudit($param.voVariable$, true);
    }

    private Result auditOrCancelAudit($param.voClzName$ $param.voVariable$, boolean audit) {
        List<String> msg = $param.serviceVariable$.checkOrUncheck($param.voVariable$.getIds(), audit);
        if (msg.size() == 0) {
            return successResult(null);
        } else {
            return new Result("1", "", msg);
        }
    }

    @ApiOperation("撤审$param.entityChinese$")
    @PostMapping("/cancelReview")
    public Result cancelReview(@RequestBody $param.voClzName$ $param.voVariable$) {
        return auditOrCancelAudit($param.voVariable$, false);
    }
}
>>