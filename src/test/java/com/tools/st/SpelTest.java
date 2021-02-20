package com.tools.st;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.math.BigDecimal;

@Slf4j
public class SpelTest {

    @Data
    public static class FormulaParam {
        private Double originalvalue = new Double("123");
        private Long minbasenum = 0L;
        private Long maxbasenum = 65535L;
        private Long mintransnum = 0L;
        private Long maxtransnum = 4L;

        private BigDecimal b1 = new BigDecimal(1234);
        private BigDecimal b2 = new BigDecimal(890);
    }

    @Test
    public void test() {
        FormulaParam param = new FormulaParam();

        //bigDecimal整数除法没有办法设置精度
        //String formula = "b1.divide(b2)";  //Non-terminating decimal expansion
        String formula = "b1 / b2";  //整数除 1
//        String formula = "b1.divide(b2, 4, 0)";  //整数除 1.3866
        ExpressionParser parser = new SpelExpressionParser();
        Expression expr = parser.parseExpression(formula);
        Object v = expr.getValue(param);
        log.info(v.toString());


        formula = "(originalvalue - minbasenum) / (maxbasenum - minbasenum) * (maxtransnum - mintransnum) + mintransnum";
        parser = new SpelExpressionParser();
        expr = parser.parseExpression(formula);
        v = expr.getValue(param);
        log.info(v.toString());

//        BigDecimal b1 = param.acqValue.subtract(param.acqMinqty);
//        BigDecimal b2 = param.floMaxqty.subtract(param.floMinqty);
//        BigDecimal b3 = param.acqMaxqty.subtract(param.acqMinqty);
//        BigDecimal b4 = b1.multiply(b2).divide(b3, 10, BigDecimal.ROUND_FLOOR).add(param.floMinqty);
//        new BigDecimal(1234L, 3)
        BigDecimal big = new BigDecimal(123);
        log.info(big.divide(new BigDecimal(65535)).toString());

    }
}
