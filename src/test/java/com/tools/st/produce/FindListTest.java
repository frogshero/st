package com.tools.st.produce;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class FindListTest {
    private static String TEMPLATE = "      <if test=\"sqlParams.indexOf('%s') >=0\"> inner join %s %s on %s.%s=%s.%s </if> \n";

    private static String SHORT_NAME = "qat";

    @Test
    public void test() {
//        extractMethods(QcAbnormalTableVO.class);
    }

    private String computDefaultIdFieldName(String fldName) {
        //要么name结尾，要么code结尾
        //updated_by, created_by不加ID
        if (fldName.endsWith("Name")) {
            return StringUtils.removeEndIgnoreCase(fldName, "Name")
                    + (fldName.equals("updatedByName") || fldName.equals("createdByName") ? "" : "Id");
        } else if (fldName.endsWith("Code")) {
            return StringUtils.removeEndIgnoreCase(fldName, "Code")
                    + (fldName.equals("updatedByCode") || fldName.equals("createdByCode") ? "" : "Id");
        } else {
            throw new RuntimeException(fldName + "必须指定来源ID字段");
        }
    }

    private String javaToDbName(String fieldName) {
        Pattern pattern = Pattern.compile("[a-z]{1}[A-Z]{1}");
        Matcher matcher = pattern.matcher(fieldName);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(0).charAt(0) + "_" + matcher.group(0).charAt(1));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

//    private void addToMap(Map<String, List<FieldDbInfo>> joinFields, String fieldName, String columnName, DataTableEnum tableEnum) {
//        String idField = tableEnum.getKeyField();
//        if (StringUtils.isBlank(idField)) {
//            idField = computDefaultIdFieldName(fieldName);
//        }
//        if (joinFields.get(idField) == null) {
//            joinFields.put(idField, Lists.newArrayList());
//        }
//        List<FieldDbInfo> fields = joinFields.get(idField);
//        FieldDbInfo fieldDbInfo = new FieldDbInfo();
//        fieldDbInfo.setFieldName(fieldName);
//        fieldDbInfo.setColumnName(columnName);
//        fieldDbInfo.setTableEnum(tableEnum);
//        fields.add(fieldDbInfo);
//    }
//
//    public void extractMethods(Class clz) {
//        Field[] flds = clz.getDeclaredFields();
//        Map<String, List<FieldDbInfo>> fieldsMap = Maps.newHashMap();
//
//        for(Field fld : flds) {
//            try {
//                SetIdName[] setIdNames = fld.getAnnotationsByType(SetIdName.class);
//                if (setIdNames != null && setIdNames.length > 0) {
//                    SetIdName setIdName = setIdNames[0];
//
//                }
//
//                JoinColumn[] joinColumns = fld.getAnnotationsByType(JoinColumn.class);
//                if (joinColumns != null && joinColumns.length > 0) {
//                    JoinColumn joinColumn = joinColumns[0];
//                    addToMap(fieldsMap, fld.getName(), joinColumn.columnName(), joinColumn.type());
//                }
//            } catch (Exception e) {
//                log.error("获取字段信息失败：{} {}", fld.getName(), e.getMessage());
//                e.printStackTrace();
//                throw new RuntimeException("获取字段信息失败");
//            }
//        }
//
//        StringBuffer sb = new StringBuffer();
//        for (Map.Entry<String, DataTableEnum> fld : nameField.entrySet()) {
//            String name = fld.getKey();
//            DataTableEnum tableEnum = fld.getValue();
//
//            String tmp = String.format(TEMPLATE,
//                    name + tableEnum.getShortName(),
//                    tableEnum.getTableName(),
//                    name + tableEnum.getShortName(),
//                    SHORT_NAME,
//                    javaToDbName(name),
//                    name + tableEnum.getShortName(),
//                    tableEnum.getKeyField()
//                    );
//            sb.append(tmp);
//        }
//        log.info(sb.toString());
//    }
}
