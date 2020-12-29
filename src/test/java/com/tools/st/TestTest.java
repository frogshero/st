package com.tools.st;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TestTest {

    @Test
    public void testStr() {
        log.info("asdf".split(",")[0]);

    }

    @Test
    public void test() {
        Set<Integer> sets = Sets.newHashSet(1, 2, 3, 4, 5, 6);
        Set<Integer> sets2 = Sets.newHashSet(3, 4, 5, 6, 7, 8, 9);
        // 交集
        System.out.println("交集为：");
        Sets.SetView<Integer> intersection = Sets.intersection(sets, sets2);
        for (Integer temp : intersection) {
            System.out.println(temp);
        }
        // 差集
        System.out.println("差集1为：");
        Sets.SetView<Integer> diff = Sets.difference(sets, sets2);
        for (Integer temp : diff) {
            System.out.println(temp);
        }
        System.out.println("差集2为：");
        Sets.SetView<Integer> diff2 = Sets.difference(sets2, sets);
        for (Integer temp : diff2) {
            System.out.println(temp);
        }
        // 并集
        System.out.println("并集为：");
        Sets.SetView<Integer> union = Sets.union(sets, sets2);
        for (Integer temp : union) {
            System.out.println(temp);
        }
    }

    @Test
    public void testReg() {
        List<String> list = Lists.newArrayList("isDeactivate",
                "isSystemInternal",
                "departmentAttrName",
                "isDeactivateDisplay",
                "isSystemInternalDisplay",
                "scheduleFlgName",
                "purchasingDemandFlgName",
                "isBoradDisplay",
                "closeFlg",
                "sexName",
                "marryName");
        list.stream().forEach(e->{
            Pattern  pattern = Pattern.compile("[A-Z]");
            Matcher matcher = pattern.matcher(e);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, "_" + matcher.group(0));
            }
            matcher.appendTail(sb);
            System.out.println("'"+sb+"',");
        });
    }
}
