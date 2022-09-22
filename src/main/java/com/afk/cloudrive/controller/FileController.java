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
import org.springframework.web.bind.annotation.*;
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
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("上传文件")
    public ResponseMessage uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                      @RequestParam("directory") String dir,
                                      @RequestParam("token") String token) {
        String username = TokenUtil.getUsername(token);
        Boolean uploadResult = fileService.uploadFile(multipartFile, dir, username);
        if (uploadResult) {
            return ResponseMessage.success(ResultEnum.SUCCESS);
        } else {
            return ResponseMessage.error(ResultEnum.UPLOAD_FILE_FAILED);
        }
    }

    @RequestMapping(value = "/loadFile", method = RequestMethod.GET)
    @ApiOperation("加载文件的http链接")
    public ResponseMessage getFile(@RequestParam("fileTimestamp") String fileTimestamp) {
        String fileHttpUrl = fileService.getFileHttpUrl(fileTimestamp);
        System.out.println("文件的http访问链接 : " + fileHttpUrl);
        return ResponseMessage.success(fileHttpUrl);
    }
}
