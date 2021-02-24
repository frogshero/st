entityVO(comment, clzName, baseClzName, modelPackage) ::= <<
package $modelPackage$;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "$comment$")
@Data
public class $clzName$ $if(baseClzName)$extends $baseClzName$$endif$ {
}
>>
