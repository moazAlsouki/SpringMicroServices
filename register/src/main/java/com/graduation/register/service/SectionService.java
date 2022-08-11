package com.graduation.register.service;

import com.graduation.register.models.Section;
import com.graduation.register.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {


    @Autowired
    SectionRepository sectionRepository;


    public List<Section> getAppartements(){
        return sectionRepository.findAll();
    }
}
