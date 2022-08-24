package com.afk.cloudrive.service.impl;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.exception.BusinessException;
import com.afk.cloudrive.mapper.FileMapper;
import com.afk.cloudrive.pojo.CloudFile;
import com.afk.cloudrive.service.FileService;
import com.afk.cloudrive.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: dengcong
 * @Date: 2022/8/24 - 08 - 24 - 10:31
 * @Description: com.afk.cloudrive.service.impl
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("${file-server.file-access-folder}")
    private String FILE_ACCESS_FOLDER;

    @Value("${ip-address}")
    private String IP_ADDRESS;

    @Value("${server.port}")
    private String PORT;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public Set<String> listFiles(String dir) {
        Set<String> fileNames = new HashSet<>();
        File fileDir = new File(dir);
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    fileNames.add(file.getName());
                }
            }
            return fileNames;
        } else {
            throw new BusinessException(ResultEnum.NOT_A_DIR);
        }
    }

    @Override
    public Boolean storeFile(String originalFileName, String filePath, String username, Date timestamp) {
        String suffix = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
        CloudFile file = new CloudFile();
        file.setId(IdUtil.getUUID());
        file.setFilename(originalFileName);
        file.setFileSuffix(suffix);
        file.setUsername(username);
        file.setFilePath(filePath);
        file.setFileUploadTime(timestamp);
        fileMapper.insert(file);
        return true;
    }

    @Override
    public String getFileHttpUrl(String fileTimestamp) {
        LambdaQueryWrapper<CloudFile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CloudFile::getFileUploadTime, fileTimestamp);
        CloudFile selectedFile = fileMapper.selectOne(queryWrapper);
        String filePath = selectedFile.getFilePath().replace("\\", "/");
        return "http://" + IP_ADDRESS + ":" + PORT  + FILE_ACCESS_FOLDER +
                    filePath;
    }

    @Override
    public String renameFile(String filename, Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        String name = filename.substring(0, filename.lastIndexOf("."));
        String tail = format.format(date);
        return name + "_" + tail + "." + suffix;
    }
}
