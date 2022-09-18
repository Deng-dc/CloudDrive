package com.afk.cloudrive;

import com.afk.cloudrive.util.MyComparator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

@SpringBootTest
class CloudriveApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testFile() {
        File path = new File("C:\\vsc-workspace\\vue\\cloud-drive");
        ArrayList<String> fileList = new ArrayList<>();
        ArrayList<String> dirList = new ArrayList<>();
        if (path.isDirectory()) {
            File[] files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file.getName());
                    } else {
                        dirList.add(file.getName());
                    }
                }
            }
        }
        dirList.sort(new MyComparator());
        for(String dirName : dirList) {
            System.out.println("dirname : " + dirName);
        }
        fileList.sort(new MyComparator());
        for(String fileName : fileList) {
            System.out.println("filename : " + fileName);
        }
        System.out.println("==================");
        ArrayList<String> result = new ArrayList<>();
        result.addAll(dirList);
        result.add("=====");
        result.addAll(fileList);
        for(String name : result) {
            System.out.println("name : " + name);
        }
    }
}
