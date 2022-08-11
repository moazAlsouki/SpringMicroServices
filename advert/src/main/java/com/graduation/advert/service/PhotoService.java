package com.graduation.advert.service;

import com.graduation.advert.handle.FileUploadUtil;
import com.graduation.advert.models.Photo;
import com.graduation.advert.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    public Photo savePhoto(Long id, String desc, MultipartFile multipartFile)throws IOException {

        String photoPath= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Photo photo =addPhoto(new Photo(id.intValue(),desc,photoPath));

        addPhoto(photo);
        String uploadDir = "advert/" +id;

        FileUploadUtil.saveFile(uploadDir, photoPath, multipartFile);
        return photo;
    }

}