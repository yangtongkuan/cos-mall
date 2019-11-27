package com.cos.common.tools;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @desc 图片上传工具类
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/31 21:27
 * @To change this template use File | Settings | File Templates.
 */
public class ImageUtils {
    private static Integer FILE_NAME_LENGTH = 18;   // 文件名随机生成多少位


    /**
     * @param uploadImage
     * @param parentFolder
     * @throws Exception
     * @desc 上传文件
     */
    public static String saveImage(MultipartFile uploadImage, String parentFolder) throws Exception {
        if (StringUtils.isEmpty(parentFolder)) {
            throw new Exception("parentFolder is not allow empty");
        }
        if (!parentFolder.endsWith(File.separator)) {
            parentFolder = parentFolder + File.separator;
        }
        String imageSuff = getImageFileSuff(uploadImage);
        File file = new File(parentFolder + createImageName(imageSuff));
        if (!checkParentFolder(file)) {
            throw new Exception("file parent is not exists");
        }
        uploadImage.transferTo(file);
        return "";
    }

    /**
     * @param fileSuf
     * @return
     * @desc 生成文件名
     */
    public static String createImageName(String fileSuf) {
        String fileName = RandomCharsUtils.getRandomChar(FILE_NAME_LENGTH);
        if (StringUtils.isNotEmpty(fileSuf)) {
            fileName = fileName + "." + fileSuf;
        }
        return fileName;
    }

    /**
     * @param uploadImage
     * @return
     * @desc 上传文件的文件后缀名
     */
    public static String getImageFileSuff(MultipartFile uploadImage) {
        if (uploadImage == null) {
            return null;
        }
        String originalFilename = uploadImage.getOriginalFilename();
        int index = originalFilename.indexOf(".");
        if (index > 0) {
            index += 1;
        }
        return index > 0 ? originalFilename.substring(index) : null;
    }

    /**
     * @param file
     * @return
     * @desc 上传到服务的文件路径 是否存在， 不存在则创建
     */
    private static boolean checkParentFolder(File file) {
        if (!file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
            return file.getParentFile().mkdirs();
        } else {
            return true;
        }
    }

}
