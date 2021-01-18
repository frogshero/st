mapper(entityPackage, daoBase, daoBasePkg, entity, postFix, voPostFix) ::= <<package com.tools.st.mapper;

$if(daoBase)$import $daoBasePkg$.$daoBase$;$endif$
import $entityPackage$.$entity$$voPostFix$;

public interface $entity$$postFix$ extends GenericMapper<$entity$$voPostFix$, Long> {
}
>>