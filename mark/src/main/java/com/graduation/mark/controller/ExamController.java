package com.graduation.mark.controller;


import com.graduation.mark.models.Exam;
import com.graduation.mark.models.Result;
import com.graduation.mark.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/mark/exam")
public class ExamController {

    @Autowired
    ExamService examService;


    @PostMapping(path = "/add")
    public Exam addMark(Exam exam)
    {
        return examService.addExam(exam);
    }

    @GetMapping("/all")
    public List<Exam> getMarks(){
        return examService.getAllExams();
    }

    @GetMapping("/get")
    public Exam getMarkById(@RequestParam("id") Long id){
        return examService.findById(id);
    }

    @GetMapping("/result")
    public Set<Result> getResult(@RequestParam long id){
        return examService.examResult(id);
    }

    @GetMapping("/res")
    public Set<Result> getResultsExam(@RequestParam int year,@RequestParam int season,@RequestParam String name)
    {
        return examService.getResult(year,season,name);
    }


}
