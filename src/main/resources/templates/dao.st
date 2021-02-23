dao(param) ::= <<
package $param.daoPackage$;

$if(param.daoBase)$import $param.daoBaseClzFullName$;$endif$
import $param.voClzFullName$;

public interface $param.daoClzName$ $if(param.daoBase)$extends $param.daoBase$<$param.voClzName$, Long>$endif$ {
}
>>