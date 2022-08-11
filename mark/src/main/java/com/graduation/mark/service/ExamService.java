package com.graduation.mark.service;

import com.graduation.mark.models.Exam;
import com.graduation.mark.models.Result;
import com.graduation.mark.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    public Exam findById(Long id){
        return examRepository.findById(id).get();
    }
    public Exam addExam(Exam exam){
        return examRepository.save(exam);
    }
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }
    public Set<Result> examResult(Long id){
        return examRepository.findById(id).get().getResults();
    }

    public Set<Result>getResult(int year,int season,String name){
        List<Exam> exams=examRepository.findByYear(year);
        for (Exam e:exams){
            if (e.getSeason()==season&&e.getCourse().getName().equals(name)){
                return e.getResults();
            }
        }
        return null;
    }
}
