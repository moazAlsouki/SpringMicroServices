package com.graduation.register.service;

import com.graduation.register.models.Course;
import com.graduation.register.models.Photo;
import com.graduation.register.models.Register;
import com.graduation.register.models.Student;
import com.graduation.register.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public Student addStudent(Student student){
        System.out.println(student.getBirth());
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id){
        Student student=studentRepository.findById(id).get();
        return student;
    }

    public List<Student> findAllStudent(){
        return studentRepository.findAll();
    }

    public boolean deleteStudent(Long id){
        Student student=findStudentById(id);
        if (student ==null)
        {
            return false;
        }
        studentRepository.delete(student);
        return true;
    }

    public Set<Register> getregister(Long id) {
        Student student=findStudentById(id);
        return student.getRegisters();
    }

    public Set<Photo> getPhoto(Long id){
        Student student=findStudentById(id);
        return student.getPhotos();
    }


    public Map<Integer,Object> getMyPassCourses(long id){
        Map<Integer,Object> courses=new HashMap<>();
        Student student=studentRepository.findById(id).get();
        for (Register r:student.getRegisters())
        {
            courses.put(r.getYear(),r.getCourses());
        }
        return courses;
    }
    public Student findByUsername(String userName){
        return studentRepository.findByUserName(userName).get();
    }
}
