package com.tools.st;

import com.tools.st.utl.FileUtl;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTest {

    @Test
    public void test() throws IOException {
        List<String> list = Files.readAllLines(Paths.get("e://tmp/ttt.txt"));
        List<String> newList = Lists.newArrayList();
        Pattern pattern = Pattern.compile("VALUES \\(([0-9,]*) 0");

        for (String str : list) {
            Matcher matcher = pattern.matcher(str);
            String s = "";
            if (matcher.find()) {
                s = str.replace(matcher.group(1), "");
            } else {
                s = str;
            }
            s = s.replace("`id`, ", "")
                    .replace("`ymc_mes_clt`.", "");
            newList.add(s);
        }
        FileUtl.writeListToFile(newList, "e://tmp/ttt2.txt");
    }
}
