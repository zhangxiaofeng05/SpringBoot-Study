package com.boot.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class FileController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/toUpload")
    public String toUpload(){
        return "upload";
    }
    @RequestMapping("/toDownload")
    public String toDownload(){
        return "download";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file){
        try {
            //如果这里没有设置路径的话，会默认保存在项目的根目录下,默认大小在1M内(1048576 bytes)
            String pre = "";
//            pre = "D:\\temp\\";
            String filePath = pre + file.getOriginalFilename();
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传成功";
    }

    @RequestMapping("/download")
    public ResponseEntity download(){
        try {
            FileSystemResource file = new FileSystemResource("D:\\temp\\avatar.jpg");
            System.out.println(file.getPath());
            HttpHeaders headers = new HttpHeaders();
            //在响应头中添加这个，设置下载文件默认的名称
            headers.add("Content-Disposition","attachment; filename=123.jpg");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(file.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
