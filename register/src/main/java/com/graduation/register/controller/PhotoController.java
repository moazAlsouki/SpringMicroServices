package com.graduation.register.controller;

import com.graduation.register.handle.FileUploadUtil;
import com.graduation.register.models.Photo;
import com.graduation.register.models.Student;
import com.graduation.register.service.PhotoService;
import com.graduation.register.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/register/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @Autowired
    StudentService studentService;

    //@PostMapping("/save")
    public Photo addPhoto(Long id,String desc,@RequestParam("image") MultipartFile multipartFile)throws IOException {
        Photo photo =photoService.savePhoto(id,desc,multipartFile);
        return photo;
    }
}
