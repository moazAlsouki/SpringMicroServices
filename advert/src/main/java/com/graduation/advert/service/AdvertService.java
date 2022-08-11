package com.graduation.advert.service;

import com.graduation.advert.models.Advert;
import com.graduation.advert.models.Faculty;
import com.graduation.advert.models.Photo;
import com.graduation.advert.repository.AdvertRepository;
import com.graduation.advert.repository.FaculityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdvertService {
    @Autowired
    AdvertRepository advertRepository;

    @Autowired
    FaculityRepository faculityRepository;
    public Advert add(Advert advert){
        return advertRepository.save(advert);
    }


    public Map<String, Object> getAdvert(long id) {
        Map<String, Object> adv=new HashMap<>();
        Advert advert=advertRepository.findById(id).get();
        Set<String> images=new HashSet<>();
        for (Photo p:advert.getPhotos()){
            String image="/advert/"+advert.getId()+"/"+p.getPicture();
            images.add(image);
        }
        adv.put("advert",advert);
        adv.put("images",images);
        return adv;
    }

    public Advert findById(long id) {
        return advertRepository.findById(id).get();
    }

    public Map<String, Object> getAdvertbyuri(String f_uri) {
        Map<String, Object> adv=new HashMap<>();
        Faculty faculty=faculityRepository.findByFuri(f_uri).get();
        for (Advert advert:faculty.getAdverts()) {
            if(advert.getExpired().after(new Date())) {
                Set<String> images=new HashSet<>();
                adv.put("advert :"+advert.getId(), advert);
                for (Photo p : advert.getPhotos()) {
                    String image = "/advert/" + advert.getId() + "/" + p.getPicture();
                    images.add(image);
                }
                adv.put("images :"+advert.getId(),images);
            }
        }
        return adv;
    }
}
