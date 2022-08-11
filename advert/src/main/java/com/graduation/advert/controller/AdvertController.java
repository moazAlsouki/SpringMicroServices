package com.graduation.advert.controller;

import com.graduation.advert.models.Advert;
import com.graduation.advert.models.Faculty;
import com.graduation.advert.models.Photo;
import com.graduation.advert.service.AdvertService;
import com.graduation.advert.service.FaculityService;
import com.graduation.advert.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/advert")
public class AdvertController {


    @Autowired
    AdvertService advertService;
    @Autowired
    PhotoService photoService;
    @Autowired
    FaculityService faculityService;

    @PostMapping("/add")
    public Map<String,Object> addadvert(Advert ad,String faculity,@RequestParam("images") MultipartFile[] images )throws Exception{
        Map<String,Object> newSt=new HashMap<>();
        System.out.println(ad.toString());
        Advert advert=advertService.add(ad);
        Faculty fa=faculityService.findByFuri(faculity);
        fa.getAdverts().add(advert);
        advert.getFaculties().add(fa);
        advertService.add(advert);
        faculityService.add(fa);
        newSt.put("advert",advert);
        Long id=advert.getId();
        for (MultipartFile mf:images){
            Photo photo=photoService.savePhoto(id," ",mf);
            newSt.put(id.toString(),photo);
        }
        return newSt;
    }
    @GetMapping("/get")
    public Map<String,Object> getAdvert(@RequestParam("id") long id){
        return advertService.getAdvert(id);
    }
    @GetMapping("/getphotos")
    public Set<Photo> getphotos(@RequestParam("id") long id){
        return advertService.findById(id).getPhotos();
    }
    @GetMapping("/geturi")
    public Map<String,Object> getAdvertByuri(@RequestParam("f") String f_uri){
        return advertService.getAdvertbyuri(f_uri);
    }
}
