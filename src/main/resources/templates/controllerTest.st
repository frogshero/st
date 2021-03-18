controllerTest(param) ::= <<
package $param.controllerPackage$;

import com.ymc.mes.basic.common.Result;
import com.ymc.mes.mold.warehouse.base.BaseTest;
import com.ymc.mes.basic.common.model.CommonBatchRequest;
import $param.voClzFullName$;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import $param.modelPackage$.$param.entityRequestClz$;

import static org.junit.Assert.*;

@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class $param.controllerClzName$Test extends BaseTest {

    private static Long newId = 0L;
    private Long entryId = 47L;
    private Long newEntryId = 48L;

    @Order(1)
    @Test
    public void a_insert() throws URISyntaxException {
        $param.entityRequestClz$ vo = new $param.entityRequestClz$();
        vo.setComId(1L);
        vo.setMoldInfoId(5L);
        vo.setOperatingTime(new Date());
        vo.setOperator(-1L);
        vo.setWarehouseEntryNo("1");
        vo.setWarehouseId(1L);
//        vo.setWarehousePosId();
        Result ret = post("$param.requestMapping$/insert", vo);
        log.info(ret.getCode());
        newId = ((Integer)ret.getData()).longValue();
    }

    public $param.entityRequestClz$ getOne() throws URISyntaxException {
        Result<MoldBomMaterialCost> ret2 = get("$param.requestMapping$/find/" + newId, new ParameterizedTypeReference<Result<$param.entityRequestClz$>>(){});
        return ret2.getData();
    }

    @Order(2)
    @Test
    public void b_update() throws URISyntaxException {
        $param.entityRequestClz$ vo = new $param.entityRequestClz$();
        vo.setId(newId);
        vo.setComId(1L);
        vo.setMoldInfoId(5L);
        vo.setOperatingTime(new Date());
        vo.setOperator(-1L);
        vo.setWarehouseEntryNo("1");
        vo.setWarehouseId(1L);
        vo.setEnableflg(1);
        vo.setRemarks("reeeeee");

        Integer quantity = Integer.parseInt(RandomStringUtils.randomNumeric(2));
        vo.setQuantity(quantity);
        Result ret = put("$param.requestMapping$/update", vo);
        log.info(ret.getCode());

        Assertions.assertEquals(getOne().getQuantity(), quantity);
    }

    @Order(3)
    @Test
    public void c_review() throws URISyntaxException {
        CommonBatchRequest vo = new CommonBatchRequest();
        vo.setIds(Lists.newArrayList(newId));
        Result ret = post("$param.requestMapping$/review", vo);
        log.info(ret.getCode());
    }

    @Order(4)
    @Test
    public void d_cancelReview() throws URISyntaxException {
        CommonBatchRequest vo = new CommonBatchRequest();
        vo.setIds(Lists.newArrayList(newId));
        Result ret = post("$param.requestMapping$/cancelReview", vo);
        log.info(ret.getCode());
    }

    @Order(5)
    @Test
    public void e_delete() throws URISyntaxException {
        CommonBatchRequest vo = new CommonBatchRequest();
        vo.setIds(Lists.newArrayList(newId));
        Result ret = post("$param.requestMapping$/delete", vo);
        log.info(ret.getCode());

        Assertions.assertNull(getOne());
    }

    @Order(6)
    @Test
    public void f_findList() throws URISyntaxException {
        List<NameValuePair> params = Lists.newArrayList(
                new BasicNameValuePair("warehouseName", "成品库")
                );
        Result ret = get("$param.requestMapping$/findList", params);
        log.info(ret.getCode());

//        Assertions.assertTrue(((List)((Map)ret.getData()).get("titleList")).size() > 0);
        Assertions.assertTrue(((List)((Map)ret.getData()).get("tableList")).size() > 0);
    }

    @Order(7)
    @Test
    public void g_exportExcel() throws URISyntaxException {
        List<NameValuePair> params = Lists.newArrayList(
                new BasicNameValuePair("warehouseName", "成品库")
                );
        Result ret = get("$param.requestMapping$/exportExcel", params);
        log.info(ret.getCode());
    }
}
>>