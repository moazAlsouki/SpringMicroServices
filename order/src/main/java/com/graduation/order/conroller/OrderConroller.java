package com.graduation.order.conroller;

import com.graduation.order.service.AcceptService;
import com.graduation.order.service.OrderListService;
import com.graduation.order.service.OrderService;
import com.graduation.order.service.PhotoService;
import com.graduation.order.models.*;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderConroller {

    @Autowired
    OrderService orderService;
    @Autowired
    AcceptService acceptService;
    @Autowired
    PhotoService photoService;
    @Autowired
    OrderListService orderListService;


    @PostMapping("/add")
    public Map<String,Object> add(@RequestParam("orderid") int oid, MultipartHttpServletRequest request, Principal principal, @Nullable String note)throws IOException {
        return orderService.addOrder(oid,request,principal);
     }

    @GetMapping("/get")
    public Map<String,Object> getOrder(@RequestParam("id") long id){
        Map<String,Object> map=new HashMap<>();
        map=orderService.getOrder(id);
        return map;
    }

    @GetMapping("/getMyAccepted")
    public List<Order> getAccepterOrders(Principal principal)
    {
        List<Order> orders=orderService.getAcceptOrder(principal.getName());
        return orders;
    }



    @GetMapping("/getMyWaiting")
    public List<Order> getWaitingOrders(Principal principal)
    {
        List<Order> orders=orderService.getWaitingOrder(principal.getName());
        return orders;
    }



    @GetMapping("/getResult")
    public String getResult(@RequestParam("id") long id){
        Order order=(Order) orderService.getOrder(id).get("order");
        if (acceptService.IsAccepted((int)order.getId())){
            return order.getResult();
        }
        return "NO";
    }
}
