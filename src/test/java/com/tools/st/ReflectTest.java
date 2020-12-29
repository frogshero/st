package com.tools.st;

import com.tools.st.utl.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Slf4j
public class ReflectTest {

//    @Test
//    public void getSet() {
//        printSets(SysComTableSelectConfig.class, "selectConfig");
//    }
//
//    private void printSets(Class clz, String vName) {
//        Arrays.stream(clz.getMethods()).forEach(e -> {
//            if (e.getName().startsWith("set")) {
//                System.out.println(vName + "." + e.getName() + "();");
//            }
//        });
//    }
//
//
//    @Test
//    public void testSet() throws Exception {
//        SysComTableGroup group = new SysComTableGroup();
//        BeanUtil.setAuditFields(group);
////        Class clz = group.getClass();
////        for (Method method : clz.getMethods()) {
////            String name = method.getName();
////            if (name.equals("setCreatedBy")) {
////                method.invoke(group, new Long(1L));
////            }
////        }
//    }
}
