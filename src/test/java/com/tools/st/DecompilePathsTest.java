package com.tools.st;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.benf.cfr.reader.api.CfrDriver;
import org.benf.cfr.reader.util.getopt.Options;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Slf4j
public class DecompilePathsTest {

    public static class MyVisitor extends SimpleFileVisitor<Path> {
        private List<String> files = Lists.newArrayList();
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);

            if (attrs.isRegularFile() && file.toFile().getAbsolutePath().endsWith(".class")) {
                log.info(file.toUri().getRawPath());
                files.add(file.toFile().getAbsolutePath());
//                log.info("name {}", file.getFileName().toFile().getAbsolutePath());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    @Test
    public void test() throws IOException {
        MyVisitor visitor = new MyVisitor();

        Path path = Paths.get("");
        Iterator<Path> iterator = path.iterator();
        Files.walkFileTree(Paths.get("E:\\wgf\\tool\\jd-gui-windows-1.6.6\\out2\\classes"),  visitor);

        CfrDriver cfrDriver = (new CfrDriver.Builder()).withBuiltOptions(null).build();
        cfrDriver.analyse(visitor.files);

    }
}
