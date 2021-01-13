package com.tools.st;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
public class LombokTest {
    @Builder
    public static class TestObj {
        private Date dd;
        private LocalDateTime ldt;
    }

    @Accessors(chain = true)
    @Setter
    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class TestObj2 {
        @NonNull
        private Date dd;
        @NonNull
        private LocalDateTime ldt;
    }

    @Accessors(chain = true)
    @RequiredArgsConstructor(staticName = "of")
    public static class TestObj3 {
        @Delegate
        @NonNull
        private TestObj2 testObj2;
    }

    @Test
    public void testLombok() {
        TestObj2 to2 = TestObj2.of(new Date(), LocalDateTime.now());
        TestObj3 to3 = TestObj3.of(TestObj2.of(new Date(), LocalDateTime.now()));
        log.info(to3.getLdt().toString());
    }

}
