package com.itheima.crm.utils;

import java.util.UUID;
import java.util.stream.StreamSupport;

/**
 * 文件上传的工具类
 * @author 若风
 * @version 1.0
 */
public class UploadUtils {

    /**
     * 解决目录下文件名重复的问题
     * @param fileName
     * @return
     */
    public static String getUuidFileName(String fileName){
        int idx = fileName.lastIndexOf(".");
        String extions = fileName.substring(idx);
        return UUID.randomUUID().toString().replace("-","")+extions;
    }

    public static String getPath(String uuidFileName){
        int code1 = uuidFileName.hashCode();
        int d1 = code1 & 0xf;
        int code2 = code1 >>> 4;
        int d2 = code2 &0xf;
        return "/"+d1+"/"+d2;
    }

}
