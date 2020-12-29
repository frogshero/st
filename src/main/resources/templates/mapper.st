mapper(entityPackage, daoBase, daoBasePkg, entity, postFix) ::= <<package com.tools.st.mapper;

$if(daoBase)$import $daoBasePkg$.$daoBase$;$endif$
import $entityPackage$.$entity$;

public interface $entity$$postFix$ extends GenericMapper<$entity$, Long> {
}
>>