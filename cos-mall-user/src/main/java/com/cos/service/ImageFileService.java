package com.cos.service;

import com.cos.common.tools.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/31 22:43
 * @To change this template use File | Settings | File Templates.
 * @des ： 图片上传
 */
@Service
public class ImageFileService {

    public void uploadImageTest(MultipartFile file){
        try {
            ImageUtils.saveImage(file,"F:\\usr\\test\\a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
