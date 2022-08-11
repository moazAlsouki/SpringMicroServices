package com.graduation.order.conroller;

import com.graduation.order.models.Accept;
import com.graduation.order.service.AcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/accept")
public class AcceptController {

    @Autowired
    AcceptService acceptService;


    @Value("${server.port}")
    int port;
    @PostMapping("/Eemployee")
    public Accept addAcceptEemployee(@RequestParam("id") long id){
        Accept accept=acceptService.setEEmployeeAccept(id);
        return accept;
    }

    @PostMapping("/Aemployee")
    public Accept addAcceptAemployee(@RequestParam("id") long id){
        Accept accept=acceptService.setAEmployeeAccept(id);
        return accept;
    }

    @PostMapping("/dean")
    public Accept addAcceptDean(@RequestParam("id") long id){
        Accept accept=acceptService.setDeanAccept(id);
        return accept;
    }

    @GetMapping("/port")
    public int getPort(){
        return port;
    }
}
