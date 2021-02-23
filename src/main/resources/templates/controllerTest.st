controllerTest(param) ::= <<
package $param.controllerPackage$;

import com.ymc.mes.basic.common.Result;
import com.ymc.mes.mold.warehouse.base.BaseTest;
import $param.voClzFullName$;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.Date;

import static org.junit.Assert.*;

@Slf4j
public class $param.controllerClzName$Test extends BaseTest {

    @Test
    public void findList() throws URISyntaxException {
        Result ret = get("$param.requestMapping$/findList");
        log.info(ret.getCode());
    }

    @Test
    public void insert() throws URISyntaxException {
        $param.voClzName$ vo = new $param.voClzName$();
        vo.setComId(1L);
        vo.setMoldInfoId(5L);
        vo.setOperatingTime(new Date());
        vo.setOperator(-1L);
        vo.setWarehouseEntryNo("1");
        vo.setWarehouseId(1L);
//        vo.setWarehousePosId();
        Result ret = post("$param.requestMapping$/insert", vo);
        log.info(ret.getCode());
    }

    @Test
    public void update() throws URISyntaxException {
        $param.voClzName$ vo = new $param.voClzName$();
        vo.setId(1L);
        vo.setComId(1L);
        vo.setMoldInfoId(5L);
        vo.setOperatingTime(new Date());
        vo.setOperator(-1L);
        vo.setWarehouseEntryNo("1");
        vo.setWarehouseId(1L);
        vo.setEnableflg(1);
        vo.setRemarks("reeeeee");
        Result ret = post("$param.requestMapping$/update", vo);
        log.info(ret.getCode());
    }

    @Test
    public void delete() throws URISyntaxException {
        $param.voClzName$ vo = new $param.voClzName$();
        vo.setIds("1");
        Result ret = post("$param.requestMapping$/delete", vo);
        log.info(ret.getCode());
    }

    @Test
    public void review() {
        $param.voClzName$ vo = new $param.voClzName$();
        vo.setIds("1");
        Result ret = post("$param.requestMapping$/review", vo);
        log.info(ret.getCode());
    }

    @Test
    public void cancelReview() {
        $param.voClzName$ vo = new $param.voClzName$();
        vo.setIds("1");
        Result ret = post("$param.requestMapping$/cancelReview", vo);
        log.info(ret.getCode());
    }
}
>>