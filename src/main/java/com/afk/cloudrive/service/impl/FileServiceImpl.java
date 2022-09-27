package com.afk.cloudrive.service.impl;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.exception.BusinessException;
import com.afk.cloudrive.mapper.FileMapper;
import com.afk.cloudrive.pojo.CloudFile;
import com.afk.cloudrive.service.FileService;
import com.afk.cloudrive.util.FileTypeCodeUtil;
import com.afk.cloudrive.util.IdUtil;
import com.afk.cloudrive.vo.FileVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: dengcong
 * @Date: 2022/8/24 - 08 - 24 - 10:31
 * @Description: com.afk.cloudrive.service.impl
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("${file-server.home-dir}")
    private String HOME_DIR;

    @Value("${file-server.file-access-folder}")
    private String FILE_ACCESS_FOLDER;

    @Value("${ip-address}")
    private String IP_ADDRESS;

    @Value("${server.port}")
    private String PORT;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public Boolean uploadFile(MultipartFile multipartFile, String currentDir, String username) {
        String fileName = multipartFile.getOriginalFilename();
        System.out.println("上传的文件名 : " + fileName);
        String absolutePath = HOME_DIR + username + "\\" + currentDir;
        File path = new File(absolutePath);
        if (! path.exists()) {
            path.mkdirs();
        }
        System.out.println("文件存放的目录 : " + absolutePath);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String fileSavePath = username + "\\" + currentDir + fileName;
            File file = new File(absolutePath + fileName);
            if (file.exists()) {
                // 同一目录下文件重名时  需重命名
                String newName = renameFile(fileName, new Date(System.currentTimeMillis()));
                File newFile = new File(absolutePath + newName);
                multipartFile.transferTo(newFile);
                String newFileSavePath = username + "\\" + currentDir + newName;
                System.out.println("after upload file timestamp : " + sf.format(newFile.lastModified()));
                storeFile(newName, newFileSavePath, username, sf.format(newFile.lastModified()));
            } else {
                multipartFile.transferTo(file);
                System.out.println("after upload file timestamp : " + sf.format(file.lastModified()));
                storeFile(fileName, fileSavePath, username, sf.format(file.lastModified()));
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public ArrayList<FileVO> listFiles(String dir) {
        File path = new File(dir);
        ArrayList<FileVO> fileList = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (path.isDirectory()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    FileVO fileVO = new FileVO();
                    String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
//                    String fileTypeCode = FileTypeCodeUtil.getFileTypeCode(fileType);
                    fileVO.setFilename(file.getName());
                    fileVO.setFileSize(String.valueOf(file.length()) + " B");
                    fileVO.setLastModifyTime(sf.format(file.lastModified()));
                    fileVO.setFileType(fileType);
                    if (FileTypeCodeUtil.getFileTypeCode(fileType) == null) {
                        fileVO.setFileTypeCode("5");
                    } else {
                        fileVO.setFileTypeCode(FileTypeCodeUtil.getFileTypeCode(fileType));
                    }
                    fileList.add(fileVO);
                } else if (file.isDirectory()) {
                    FileVO fileVO = new FileVO();
                    fileVO.setFilename(file.getName());
                    fileVO.setFileSize("-");
                    fileVO.setLastModifyTime(sf.format(file.lastModified()));
                    fileVO.setFileType("directory");
                    fileVO.setFileTypeCode("0");
                    fileList.add(fileVO);
                } else {
                    throw new BusinessException(ResultEnum.UNKNOWN_FILE_TYPE);
                }
            }
            return fileList;
        } else {
            throw new BusinessException(ResultEnum.NOT_A_DIR);
        }
    }

    @Override
    public Boolean storeFile(String originalFileName, String filePath, String username, String timestamp) {
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
    public String getFileHttpUrl(String fileTimestamp, String filename) {
        LambdaQueryWrapper<CloudFile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CloudFile::getFileUploadTime, fileTimestamp);
        queryWrapper.eq(CloudFile::getFilename, filename);
        CloudFile selectedFile = fileMapper.selectOne(queryWrapper);
        System.out.println(selectedFile.getFilePath());
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

    @Override
    public Boolean createNewDir(String username, String path) {
        String absolutePath = HOME_DIR + username + "\\" + path;
        File createPath = new File(absolutePath);
        if (! createPath.exists()) {
            boolean mkdirResult = createPath.mkdirs();
            if (mkdirResult) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
