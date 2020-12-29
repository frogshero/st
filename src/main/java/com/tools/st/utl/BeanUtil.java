package com.tools.st.utl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class BeanUtil {

    public static void setAuditFields(Object o) throws Exception {
        Class clz = o.getClass();

            for (Method method : clz.getMethods()) {
                String name = method.getName();
                try {
                    switch (name) {
                        case "setEnableflg":
                            method.invoke(o, new Integer(1));
                            break;
                        case "setComId":
                        case "setCreatedBy":
                        case "setUpdatedBy":
                            method.invoke(o, new Long(1L));
                            break;
                        case "setCreatedTime":
                        case "setUpdatedTime":
                            method.invoke(o, new Date());
                            break;
                    }
                } catch (Exception e) {
                    throw new Exception(method + " set error " + e.getMessage());
                }

            }
    }
}
