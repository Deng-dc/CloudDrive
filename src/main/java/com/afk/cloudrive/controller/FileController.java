package com.afk.cloudrive.controller;

import com.afk.cloudrive.enums.ResultEnum;
import com.afk.cloudrive.response.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author: dengcong
 * @Date: 2022/8/23 - 08 - 23 - 20:35
 * @Description: com.afk.cloudrive.controller
 */
@RestController
@Api(tags = "文件")
public class FileController {
    @Value("${file-server.home-dir}")
    private String homeDir;

    @Value("${file-server.recycle-bin-dir}")
    private String recycleBinDir;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("上传文件")
    public ResponseMessage uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                      @RequestParam("directory") String dir) {
        String fileName = multipartFile.getOriginalFilename();
        String directory = dir.replace("\\", "/");
        String absolutePath = homeDir + directory;
        File file = new File(absolutePath + fileName);
        return ResponseMessage.success(ResultEnum.SUCCESS);
    }
}
