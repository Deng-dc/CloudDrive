package com.afk.cloudrive.util;


import java.util.HashMap;

/**
 * @Author: dengcong
 * @Date: 2022/9/27 - 09 - 27 - 20:28
 * @Description: com.afk.cloudrive.util
 */
public class FileTypeCodeUtil {
    private static HashMap<String, String> map = new HashMap<>();

    public static void setMap() {
        map.put("jpg", "1");
        map.put("jpeg", "1");
        map.put("png", "1");
        map.put("pdf", "2");
        map.put("txt", "2");
        map.put("doc", "2");
        map.put("docx", "2");
        map.put("xlsx", "2");
        map.put("ppt", "2");
        map.put("pptx", "2");
        map.put("mp3", "3");
        map.put("wav", "3");
        map.put("cda", "3");
        map.put("flac", "3");
        map.put("mp4", "4");
        map.put("avi", "4");
        map.put("flv", "4");
        map.put("mov", "4");
        map.put("mkv", "4");
        map.put("mpeg", "4");
    }

    public static String getFileTypeCode(String key) {
        FileTypeCodeUtil.setMap();
        return map.get(key);
    }
}
