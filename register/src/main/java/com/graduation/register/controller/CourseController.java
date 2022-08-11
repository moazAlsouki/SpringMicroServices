package com.graduation.register.controller;

import com.graduation.register.models.Course;
import com.graduation.register.models.Register;
import com.graduation.register.service.CourseService;
import com.graduation.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    RegisterService registerService;


    @PostMapping("/add")
    public Course pass(int exam_number,String course_name,int result){
        Register register=registerService.findByExam(exam_number);
        Course course=new Course(register.getId().intValue(),course_name,result );
        return courseService.addPassCourse(course);
    }
}
