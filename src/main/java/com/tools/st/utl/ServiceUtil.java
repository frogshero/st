package com.tools.st.utl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceUtil {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat REPORT_TIME_FMT = new SimpleDateFormat("yyyyMMddHHmmss");
    public static String EXCEL_EXT = ".xlsx";
    public static String EXPORTED_DIR = "EXCEL";

    public static String getExportFileName(String prefix, String ext) {
        return prefix + "_" + getUniStr() + ext;
    }

    public static String getUniStr() {
        return REPORT_TIME_FMT.format(new Date()) + "_" + RandomStringUtils.randomAlphabetic(4);
    }

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
    public static <E> String getIds(List<E> voList, Function<E, String> supplier) {
        return voList.stream().map(supplier).collect(Collectors.joining(","));
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

    public static Long nvl(Long val) {
        return val == null ? 0L : val;
    }

    public static Double nvl(Double val) {
        return val == null ? 0L : val;
    }

    public static String nvl(String val) {
        return val == null ? "" : val;
    }
}
