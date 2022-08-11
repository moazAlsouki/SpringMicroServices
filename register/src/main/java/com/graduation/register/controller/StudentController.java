package com.graduation.register.controller;

import com.graduation.register.models.Course;
import com.graduation.register.models.Photo;
import com.graduation.register.models.Register;
import com.graduation.register.models.Student;
import com.graduation.register.service.PhotoService;
import com.graduation.register.service.RegisterService;
import com.graduation.register.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/register/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    RegisterService registerService;
    @Autowired
    PhotoService photoService;
    @PostMapping("/add")
    public Student addstudent(Student student)
    {
        System.out.println(student);
        return studentService.addStudent(student);
    }

    @DeleteMapping("/delete")
    public boolean deletestudent(@RequestParam Long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/get")
    public Map<String ,Object> getStudent(@RequestParam("id") Long id){
        Map<String ,Object> map=new HashMap<>();
        map.put("student",studentService.findStudentById(id));
        return map;
    }

    @GetMapping("/all")
    public List<Student> getStudent(){
        return studentService.findAllStudent();
    }

    @GetMapping("/registers")
    public Set<Register> getRegister(@RequestParam("id") Long id){
        return studentService.getregister(id);
    }
    @GetMapping("/photos")
    public Set<Photo> getphoto(@RequestParam("id") Long id){
        return studentService.getPhoto(id);
    }


    @PostMapping("/register")
    public Map<String ,Object> registerstudent(Student st, @RequestParam("icard") MultipartFile Icard, @RequestParam("mypic") MultipartFile personalphoto, @RequestParam("highschool") MultipartFile highschool)throws Exception{
        Map<String,Object> newSt=new HashMap<>();
        Student student=studentService.addStudent(st);
        newSt.put("Student",student);
        long id=student.getId();
        Photo p1=photoService.savePhoto(id,"Icard",Icard);
        Photo p2=photoService.savePhoto(id,"personal picture",personalphoto);
        Photo p3=photoService.savePhoto(id,"High School",highschool);
        newSt.put("icard",p1);
        newSt.put("personal pic",p2);
        newSt.put("high school",p3);
        return newSt;
    }

    @GetMapping("/get/pass")
    public Map<Integer, Object> getMyPassCourse(@RequestParam("id") Long id){

        return studentService.getMyPassCourses(id);
    }

    @PostMapping("/get/suspend")
    public Map<String,Object> get(@RequestParam("name") String studentname){
        System.out.println(studentname);
        Student student=studentService.findByUsername(studentname);
        Map<String ,Object> map=new HashMap<>();
        map.put("student",student);
        Date date=new Date();
        map.put("date",date);
        Register register=registerService.getLastRegister(student.getId().intValue());
        map.put("section",register.getSection().getName());
        map.put("year",register.getYear());
        return map;
    }

}
