package com.tools.st;

import com.tools.st.entity.DbTableFieldConfig;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    @Test
    public void testSet() {
        for (Field f : DbTableFieldConfig.class.getDeclaredFields()) {
            System.out.println("vo.set" + StringUtils.capitalize(f.getName() + "();"));
        }
    }
}
