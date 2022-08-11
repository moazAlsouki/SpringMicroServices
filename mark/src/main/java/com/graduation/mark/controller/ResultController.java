package com.graduation.mark.controller;

import com.graduation.mark.exel.ExcelHelper;
import com.graduation.mark.models.Exam;
import com.graduation.mark.models.Result;
import com.graduation.mark.service.ExamService;
import com.graduation.mark.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/mark/result")
public class ResultController {
    @Autowired
    ResultService resultService;
    @Autowired
    ExamService examService;
    @PostMapping("/upload")
    public String uploadFile(MultipartHttpServletRequest request, Exam exam){
        String message = "";
        Exam exam1 =examService.addExam(exam);
        MultipartFile file= request.getFile("file");
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                resultService.save(file,  exam1.getId().intValue());
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return message;
            }catch (Exception e){
                message="couldnt Upload Excel File : "+e.getMessage();
                System.err.println(message);
                return message;
            }

        }
        message = "Please upload an excel file!";
        return message;
    }

    @GetMapping("/my")
    public Result findMyResult(@RequestParam int exam_id,@RequestParam int id){
        return resultService.myResult(exam_id,id);
    }

    @GetMapping("/getresult")
    public List<Result> get (@RequestParam("id") int eid) {
        return resultService.getResultByEID(eid);

    }



}
