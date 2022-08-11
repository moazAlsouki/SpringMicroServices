package com.graduation.register.service;

import com.graduation.register.handle.FileUploadUtil;
import com.graduation.register.models.Photo;
import com.graduation.register.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public Photo addPhoto(Photo photo){
        return photoRepository.save(photo);
    }
    public Photo findPhotoById(Long id){
        Photo photo=photoRepository.findById(id).get();
        return photo;
    }
    public List<Photo> findAllPhoto(){
        return photoRepository.findAll();
    }
    public boolean deletePhoto(Long id){
        Photo photo= findPhotoById(id);
        if (photo ==null)
        {
            return false;
        }
        photoRepository.delete(photo);
        return true;
    }
    public Photo savePhoto(Long id,String desc,MultipartFile multipartFile)throws IOException {

        String photoPath= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Photo photo =addPhoto(new Photo(id.intValue(),desc,photoPath));

        addPhoto(photo);
        String uploadDir = "student/" +id+ photo.getDescription();

        FileUploadUtil.saveFile(uploadDir, photoPath, multipartFile);
        return photo;
    }

}
