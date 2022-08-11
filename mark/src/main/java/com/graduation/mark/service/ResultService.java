package com.graduation.mark.service;

import com.graduation.mark.exel.ExcelHelper;
import com.graduation.mark.models.Exam;
import com.graduation.mark.models.Result;
import com.graduation.mark.repository.ExamRepository;
import com.graduation.mark.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    ResultRepository resultRepository;
    @Autowired
    ExamRepository examRepository;
    public void save(MultipartFile file,int exam_id) {
        try {
            List<Result> results = ExcelHelper.excelToResult(file.getInputStream(),exam_id);
            for (Result r:results) {

                System.err.println(r.toString());
                resultRepository.save(r);
            }
        } catch (IOException e) {
            String message="fail to store excel data: " + e.getMessage();
            System.err.println(message);
            throw new RuntimeException(message);
        }
    }

    public Result myResult(int exam_id,int student_id) {
        Exam exam=examRepository.findById((long) exam_id).get();
        if(!exam.getResults().isEmpty())
            for (Result r:exam.getResults()){
                if (r.getStudentid()==student_id)
                    return r;
            }
        return null;
    }


    public List<Result> getResultByEID(int eid) {
        List<Result> results=  resultRepository.findByStudentid(eid);
        return results;
    }
}
