generator(tabName,entityName) ::= "<table tableName=\"$tabName$\"
       domainObjectName=\"$entityName$\" mapperName=\"$entityName$Mapper\"
       enableCountByExample=\"false\" enableUpdateByExample=\"false\" enableDeleteByExample=\"false\"
       enableSelectByExample=\"false\" selectByExampleQueryId=\"false\">
    <generatedKey column=\"id\" sqlStatement=\"MySql\" identity=\"true\"/>
</table>

"