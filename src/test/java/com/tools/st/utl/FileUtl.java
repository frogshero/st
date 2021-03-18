package com.tools.st.utl;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class FileUtl {

    public static void writeStrToFile(String content, String file) throws IOException {
        File path = new File(FilenameUtils.getFullPath(file));
        if (!path.exists()) {
            path.mkdir();
        }
        try (FileWriter fwMapper = new FileWriter(file)) {
            fwMapper.write(content);
        }
    }

    public static void writeListToFile(List<String> content, String file) throws IOException {
        File path = new File(FilenameUtils.getFullPath(file));
        if (!path.exists()) {
            path.mkdir();
        }
        try (FileWriter fwMapper = new FileWriter(file)) {
            content.stream().forEach(str -> {
                try {
                    fwMapper.append(str + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
