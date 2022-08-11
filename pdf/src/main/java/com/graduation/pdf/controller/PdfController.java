package com.graduation.pdf.controller;

import com.graduation.pdf.service.PdfGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    PdfGenerateService generatePdfFile;
    @Autowired
    RestTemplate restTemplate;


    @Value("${server.port}")
    int port;

    private static final String EXTENSION = ".pdf";
    private static final String SERVER_LOCATION = "C:/Users/MOAZ ALSOUKI/Desktop/Graduation/Services/pdf/pdf/";

    @GetMapping("/generate")
    public String  getpdf(@RequestParam("id") int id){
        Map<String ,Object> data= restTemplate.getForObject("http://localhost:8001/student/get?id="+id,Map.class);
        String path= generatePdfFile.generatePdfFile("quotation", data, id+".pdf");
        System.out.println(data.toString());
        return path;
    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@RequestParam("pdf") String pdf) throws IOException {
        File file = new File( SERVER_LOCATION+ pdf + EXTENSION);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+pdf+EXTENSION);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @GetMapping("/port")
    public int getPort(){
        return port;
    }


    @GetMapping("/generate/suspend")
    public String  getpdfsuspend(@RequestParam("name") String name,@RequestHeader("Authorization") String token){
        System.out.println(name);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.put("Authorization", Collections.singletonList(token));
        HttpEntity<Void> request=new HttpEntity<>(httpHeaders);
        Map<String ,Object> data= restTemplate.postForObject("http://localhost:8001/register/student/get/suspend?name="+name,request,Map.class);
        System.out.println(name);
        System.out.println(data.toString());
        String path= generatePdfFile.generatePdfFile("suspension", data, name+".pdf");
        return path;
    }



}
