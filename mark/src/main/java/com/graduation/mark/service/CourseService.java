package com.graduation.mark.service;

import com.graduation.mark.models.Course;
import com.graduation.mark.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Course findById(Long id){
        return courseRepository.findById(id).get();
    }
    public Course addCourse(Course course){
        return courseRepository.save(course);
    }
    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }
}

