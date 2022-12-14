package com.afk.cloudrive.controller;

import com.afk.cloudrive.dto.PathDTO;
import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.exception.BusinessException;
import com.afk.cloudrive.response.ResponseMessage;
import com.afk.cloudrive.service.FileService;
import com.afk.cloudrive.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: dengcong
 * @Date: 2022/8/23 - 08 - 23 - 20:35
 * @Description: com.afk.cloudrive.controller
 */
@RestController
@Api(tags = "文件")
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     * @param multipartFile
     * @param dir
     * @param token
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("上传文件")
    public ResponseMessage uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                      @RequestParam("directory") List<String> dir,
                                      @RequestParam("token") String token) {
        String username = TokenUtil.getUsername(token);
        StringBuilder distDir = new StringBuilder();
        for (String s : dir) {
            distDir.append(s).append("\\");
        }
        Boolean uploadResult = fileService.uploadFile(multipartFile, distDir.toString(), username);
        if (uploadResult) {
            return ResponseMessage.success(ResultEnum.SUCCESS);
        } else {
            return ResponseMessage.error(ResultEnum.UPLOAD_FILE_FAILED);
        }
    }

    /**
     * 用户在目录下创建新的文件夹
     * @param path
     * @param newDir
     * @return
     */
    @RequestMapping(value = "/createNewDir", method = RequestMethod.POST)
    public ResponseMessage createNewDirectory(@RequestParam("path") List<String> path,
                                              @RequestParam("newDir") String newDir,
                                              @RequestParam("token") String token) {
        String username = TokenUtil.getUsername(token);
        StringBuilder distDir = new StringBuilder();
        for (String s : path) {
            distDir.append(s).append("\\");
        }
        String s = distDir.toString() + newDir;
        Boolean createResult = fileService.createNewDir(username, s);
        if (createResult) {
            return ResponseMessage.success(newDir);
        } else {
            return ResponseMessage.error(ResultEnum.CREATE_DIR_FAILED);
        }
    }

//    /**
//     * 加载文件的http链接
//     * @param fileTimestamp
//     * @return
//     */
//    @RequestMapping(value = "/loadFile", method = RequestMethod.GET)
//    @ApiOperation("加载文件的http链接")
//    public ResponseMessage getFile(@RequestParam("fileTimestamp") String fileTimestamp) {
//        String fileHttpUrl = fileService.getFileHttpUrl(fileTimestamp);
//        System.out.println("文件的http访问链接 : " + fileHttpUrl);
//        return ResponseMessage.success(fileHttpUrl);
//    }

    /**
     * 获取文件加载的http链接
     * @param fileTimeStamp
     * @param filename
     * @return
     */
    @RequestMapping(value = "/loadFile", method = RequestMethod.GET)
    public ResponseMessage getFileUrl(@RequestParam("fileTimestamp") String fileTimeStamp,
                                      @RequestParam("filename") String filename) {
        String fileHttpUrl = fileService.getFileHttpUrl(fileTimeStamp, filename);
        System.out.println("文件的http访问链接 : " + fileHttpUrl);
        return ResponseMessage.success(fileHttpUrl);
    }
}
