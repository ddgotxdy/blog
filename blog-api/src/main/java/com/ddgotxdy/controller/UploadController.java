package com.ddgotxdy.controller;

import com.ddgotxdy.util.JwtUtil;
import com.ddgotxdy.util.QiNiuUtil;
import com.ddgotxdy.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


/**
 * @author: ddgo
 * @description: 图片上传
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private QiNiuUtil qiNiuUtil;

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file){
        String fileName = JwtUtil.getUUID() + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        boolean upload = qiNiuUtil.upload(file, fileName);
        if (upload) {
            return Result.success(qiNiuUtil.url + fileName);
        }
        return Result.fail(20001,"上传失败");
    }
}