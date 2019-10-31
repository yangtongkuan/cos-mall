package com.cos.web.controller;

import com.cos.common.tools.AjaxResult;
import com.cos.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/31 22:49
 * @To change this template use File | Settings | File Templates.
 */
@RestController
public class UploadTestController {

    @Autowired
    private ImageFileService imageFileService;

    @RequestMapping("/image/upload/test")
    public Object uploadImage(MultipartFile file) {
        imageFileService.uploadImageTest(file);
        return AjaxResult.successResult();
    }

}
