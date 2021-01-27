import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StreamTest {

    @Test
    public void test() {
        List<String> list = Lists.newArrayList("isDeactivate",
                "isSystemInternal");
        List<String> ret = list.stream().flatMap(e -> Arrays.stream(e.split(""))).collect(Collectors.toList());
        log.info("{}", ret.size());
    }
}
