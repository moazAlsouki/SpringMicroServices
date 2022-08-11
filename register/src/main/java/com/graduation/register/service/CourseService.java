package com.graduation.register.service;

import com.graduation.register.models.Course;
import com.graduation.register.models.Register;
import com.graduation.register.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public Course addPassCourse(Course course){
        return courseRepository.save(course);
    }
}
