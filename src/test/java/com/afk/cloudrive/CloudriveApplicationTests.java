package com.afk.cloudrive;

import com.afk.cloudrive.vo.FileVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@SpringBootTest
class CloudriveApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testFile() {
        File path = new File("C:\\vsc-workspace\\vue\\cloud-drive");
//        ArrayList<String> fileList = new ArrayList<>();
//        ArrayList<String> dirList = new ArrayList<>();
        ArrayList<FileVO> fileList = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (path.isDirectory()) {
            File[] files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
//                        fileList.add(file.getName());
                        System.out.println("file " + file.getName() + " size : " + file.length() +
                                " time: " + sf.format(file.lastModified()));
                        FileVO fileVO = new FileVO();
                        fileVO.setFilename(file.getName());
                        fileVO.setWhetherDir(false);
                        fileVO.setFileSize(String.valueOf(file.length()));
                        fileVO.setLastModifyTime(sf.format(file.lastModified()));
                        System.out.println("file vo : " + fileVO);
                    } else if (file.isDirectory()){
                        FileVO fileVO = new FileVO();
                        fileVO.setFilename(file.getName());
                        fileVO.setWhetherDir(true);
                        fileVO.setFileSize("-");
                        fileVO.setLastModifyTime(sf.format(file.lastModified()));
                        System.out.println("dir vo : " + fileVO);
                    } else {
                        System.out.println("未知的文件类型");
                    }
                }
            }
        }
//        dirList.sort(new MyComparator());
//        for(String dirName : dirList) {
//            System.out.println("dirname : " + dirName);
//        }
//        fileList.sort(new MyComparator());
//        for(String fileName : fileList) {
//            System.out.println("filename : " + fileName);
//        }
//        System.out.println("==================");
//        ArrayList<String> result = new ArrayList<>();
//        result.addAll(dirList);
//        result.add("=====");
//        result.addAll(fileList);
//        for(String name : result) {
//            System.out.println("name : " + name);
//        }
    }
}
