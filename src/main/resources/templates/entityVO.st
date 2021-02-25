entityVO(comment, clzName, baseClzName, modelPackage, idField) ::= <<
package $modelPackage$;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "$comment$")
@Data
public class $clzName$ $if(baseClzName)$extends $baseClzName$$endif$ {
$if(idField)$
    @ApiModelProperty(value = "id,修改时必传")
    private Long id;$endif$

}
>>
