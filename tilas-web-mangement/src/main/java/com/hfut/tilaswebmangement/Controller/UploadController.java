package com.hfut.tilaswebmangement.Controller;

import com.hfut.tilaswebmangement.pojo.Result;
import com.hfut.tilaswebmangement.utils.AliyunOssOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    private AliyunOssOperator aliyunOssOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件:{}",file.getOriginalFilename());
        String url=aliyunOssOperator.upload(file.getBytes(),file.getOriginalFilename());
        return Result.success(url);
    }
}
