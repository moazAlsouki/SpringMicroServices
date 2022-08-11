package com.graduation.advert.service;

import com.graduation.advert.models.Faculty;
import com.graduation.advert.repository.FaculityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaculityService {
    @Autowired
    FaculityRepository faculityRepository;
    public Faculty findByFuri(String faculity) {
        return  faculityRepository.findByFuri(faculity).get();
    }

    public Faculty add(Faculty fa) {
        return faculityRepository.save(fa);
    }
}
