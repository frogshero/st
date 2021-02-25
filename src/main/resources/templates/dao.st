dao(param) ::= <<
package $param.daoPackage$;

$if(param.daoBase)$import $param.daoBaseClzFullName$;$endif$
import $param.voClzFullName$;
import $param.modelPackage$.$param.javaName$;
import $param.modelPackage$.$param.listRequestClz$;
import java.util.List;

public interface $param.daoClzName$ $if(param.daoBase)$extends $param.daoBase$<$param.voClzName$, Long>$endif$ {
$if(!param.daoBase)$
	int deleteByPrimaryKey(Long id);

	int insert($param.javaName$ record);

	$param.javaName$ selectByPrimaryKey(Long id);

	int updateByPrimaryKey($param.javaName$ record);
$endif$

    List<$param.voClzName$> findList($param.listRequestClz$ request);
}
>>