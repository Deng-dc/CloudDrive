package com.afk.cloudrive.controller;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.exception.BusinessException;
import com.afk.cloudrive.response.ResponseMessage;
import com.afk.cloudrive.service.FileService;
import com.afk.cloudrive.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: dengcong
 * @Date: 2022/8/23 - 08 - 23 - 20:35
 * @Description: com.afk.cloudrive.controller
 */
@RestController
@Api(tags = "文件")
public class FileController {
    @Value("${file-server.home-dir}")
    private String HOME_DIR;

    @Value("${file-server.recycle-bin-dir}")
    private String RECYCLE_BIN_DIR;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("上传文件")
    public ResponseMessage uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                      @RequestParam("directory") String dir,
                                      @RequestParam("token") String username) {
        String fileName = multipartFile.getOriginalFilename();
        System.out.println("上传的文件名 : " + fileName);
//        String username = TokenUtil.getUsername(token);
        String absolutePath = HOME_DIR + username + "\\" + dir;
        File path = new File(absolutePath);
        if (! path.exists()) {
            path.mkdirs();
        }
        System.out.println("文件存放的目录 : " + absolutePath);
        try {
            String fileSavePath = username + "\\" + dir + "\\" + fileName;
            File file = new File(absolutePath + "\\" + fileName);
            if (file.exists()) {
                // 同一目录下文件重名时  需重命名
                String newName = fileService.renameFile(fileName, new Date(System.currentTimeMillis()));
                File newFile = new File(absolutePath + "\\" + newName);
                multipartFile.transferTo(newFile);
                String newFileSavePath = username + "\\" + dir + "\\" + newName;
                fileService.storeFile(newName, newFileSavePath, username, new Date(System.currentTimeMillis()));
            } else {
                multipartFile.transferTo(file);
                fileService.storeFile(fileName, fileSavePath, username, new Date(System.currentTimeMillis()));
            }
            return ResponseMessage.success(ResultEnum.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseMessage.error(ResultEnum.UPLOAD_FILE_FAILED);
        }
    }

    @RequestMapping(value = "loadFile", method = RequestMethod.GET)
    @ApiOperation("加载文件")
    public ResponseMessage getFile(@RequestParam("fileTimestamp") String fileTimestamp) {
        String fileHttpUrl = fileService.getFileHttpUrl(fileTimestamp);
        System.out.println("文件的http访问链接 : " + fileHttpUrl);
        return ResponseMessage.success(fileHttpUrl);
    }
}
