package com.tools.st.utl;

import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceUtl {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static List<Long> parseIds(String ids) {
        return Arrays.stream(ids.split(",")).map(e -> Long.parseLong(e)).collect(Collectors.toList());
    }

    public static <E> List<Long> getIdList(List<E> voList, LongIdFunc<E> func) {
        return voList.stream().map(func).collect(Collectors.toList());
    }

    public static <E> Set<Long> getIdSet(List<E> voList, LongIdFunc<E> func) {
        return voList.stream().map(func).collect(Collectors.toSet());
    }

    public static <E> String getIds(List<E> voList, StrIdFunc<E> func) {
        List<String> list = voList.stream().map(func).collect(Collectors.toList());
        return String.join(",", list);
    }

    public static <E> List<Long> getNotNullIdList(List<E> voList, Predicate<E> notNull, LongIdFunc<E> func) {
        return voList.stream().filter(notNull).map(func).collect(Collectors.toList());
    }

    public static String dateToStr(Date dd) {
        return dateFormat.format(dd);
    }

    public static Date afterDays(int days) {
        return DateUtils.addDays(new Date(), days);
    }

    public static Double BDToDouble(BigDecimal bd) {
        return bd == null ? 0 : bd.doubleValue();
    }
}
