package com.graduation.register.controller;

import com.graduation.register.models.Register;
import com.graduation.register.models.Student;
import com.graduation.register.service.RegisterService;
import com.graduation.register.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @Autowired
    StudentService studentService;

    @Value("${server.port}")
    int port;

    @PostMapping("/add")
    public Register addRegister(Register register){
        return registerService.addRegister(register);
    }

    @GetMapping("/get")
    public Register getRegister(@RequestParam("id") Long id){

        return registerService.findRegisterById(id);
    }

    @GetMapping("/all")
    public Set<Register> getStudentIsRegister(@RequestParam("id") Long id){
        Student student=studentService.findStudentById(id);
        return student.getRegisters();
    }

    @GetMapping("/get/exam")
    public Register getRegisterByExam(@RequestParam("id") int id){

        return registerService.findByExam(id);
    }

    @GetMapping("/port")
    public int getPort(){
        return port;
    }
}
