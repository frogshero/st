package com.tools.st.utl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtl {

    public static String getObjName(String dbName) {
        Pattern pattern = Pattern.compile("\\_.?");
        Matcher matcher = pattern.matcher(dbName.toLowerCase());
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(0).substring(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String getShortName(String dbName) {
        Pattern pattern = Pattern.compile("\\_.?");
        Matcher matcher = pattern.matcher(dbName.toLowerCase());
        StringBuffer sb = new StringBuffer(String.valueOf(dbName.charAt(0)));
        while (matcher.find()) {
            sb.append(matcher.group(0).substring(1));
        }
        return sb.toString();
    }
}
