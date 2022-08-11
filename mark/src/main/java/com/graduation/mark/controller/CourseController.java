package com.graduation.mark.controller;

import com.graduation.mark.models.Course;
import com.graduation.mark.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mark/course")
public class CourseController {

    @Autowired
    CourseService courseService;


    @Value("${server.port}")
    int port;

    @GetMapping ("/port")
    public int getPort(){
        return port;
    }

    @GetMapping("/all")
    public List<Course> getCourses()
    {
        return courseService.getAllCourse();
    }

    @GetMapping("/get")
    public Course findCourse(@RequestParam("id") Long id){
        return courseService.findById(id);
    }

    @PostMapping("add")
    public Course addCourse(Course course)
    {
        System.out.println(course);
        return courseService.addCourse(course);
    }
}
