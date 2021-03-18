controllerTest(param) ::= <<
package $param.controllerPackage$;

import com.ymc.mes.basic.common.Result;
import com.ymc.mes.mold.base.BaseTest;
import com.ymc.mes.basic.common.model.CommonBatchRequest;
import $param.voClzFullName$;
import $param.entityClzFullName$;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.junit.jupiter.api.*;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;

import org.springframework.core.ParameterizedTypeReference;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;

@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class $param.controllerClzName$Test extends BaseTest {

    private static Long newId = 0L;
    private static String URL_ROOT = "$param.requestMapping$";

    private Long entryId = 47L;
    private Long newEntryId = 48L;

    @Order(1)
    @Test
    public void a_insert() throws URISyntaxException {
        $param.javaName$ vo = new $param.javaName$();
        Result ret = post(URL_ROOT + "/insert", vo);
        log.info(ret.getCode());
        newId = ((Integer)ret.getData()).longValue();
    }

    public $param.javaName$ getOne() throws URISyntaxException {
        Result<$param.javaName$> ret2 = get(URL_ROOT + "/find/" + newId, new ParameterizedTypeReference<Result<$param.javaName$\>>(){});
        return ret2.getData();
    }

    @Order(2)
    @Test
    public void b_update() throws URISyntaxException {
        $param.javaName$ vo = new $param.javaName$();
        vo.setId(newId);
        Integer quantity = Integer.parseInt(RandomStringUtils.randomNumeric(2));
        vo.setQuantity(quantity);
        Result ret = put(URL_ROOT + "/update", vo);
        log.info(ret.getCode());

        Assertions.assertEquals(getOne().getQuantity(), quantity);
    }

    @Order(3)
    @Test
    public void c_review() throws URISyntaxException {
        CommonBatchRequest vo = new CommonBatchRequest();
        vo.setIds(Lists.newArrayList(newId));
        Result ret = post(URL_ROOT + "/review", vo);
        log.info(ret.getCode());
    }

    @Order(4)
    @Test
    public void d_cancelReview() throws URISyntaxException {
        CommonBatchRequest vo = new CommonBatchRequest();
        vo.setIds(Lists.newArrayList(newId));
        Result ret = post(URL_ROOT + "/cancelReview", vo);
        log.info(ret.getCode());
    }

    @Order(5)
    @Test
    public void e_delete() throws URISyntaxException {
        CommonBatchRequest vo = new CommonBatchRequest();
        vo.setIds(Lists.newArrayList(newId));
        Result ret = post(URL_ROOT + "/delete", vo);
        log.info(ret.getCode());

        Assertions.assertNull(getOne());
    }

    @Order(6)
    @Test
    public void f_findList() throws URISyntaxException {
        List<NameValuePair> params = Lists.newArrayList(
                new BasicNameValuePair("warehouseName", "成品库")
                );
        Result ret = get(URL_ROOT + "/findList", params);
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
        Result ret = get(URL_ROOT + "/exportExcel", params);
        log.info(ret.getCode());
    }
}
>>