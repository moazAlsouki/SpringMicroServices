package com.graduation.order.service;

import com.graduation.order.models.Accept;
import com.graduation.order.models.Order;
import com.graduation.order.models.OrderList;
import com.graduation.order.models.Photo;
import com.graduation.order.repository.OrderListRepository;
import com.graduation.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    AcceptService acceptService;
    @Autowired
    OrderListService orderListService;
    @Autowired
    PhotoService photoService;
    @Autowired
    RestTemplate restTemplate;

    @Value("${gateway.address}")
    private String gatewayAddress;
    @Value("${gateway.port}")
    private String gatewayPort;
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }


    public HashMap<String,Object>  getOrder(long id){
        Order order =orderRepository.findById(id).get();
        HashMap<String,Object> map=new HashMap<>();
        List<String> images=new ArrayList<>();
        for (Photo p:order.getPhotos()){
            images.add("/order/"+p.getOrderId()+"/"+p.getPicture());
        }
        map.put("order",order);
        map.put("images",images);
        return map;
    }

    public List<Order> getMyOrders(String name){
      return orderRepository.findByStudentName(name);
    }

    public List<Order> getAcceptOrder(String name){
        List<Order> orders=orderRepository.findByStudentName(name);
        List<Order> orderslist=new ArrayList<>();
        for (Order o:orders){
            boolean accept=true;
            for(Accept ac:o.getAccepts()){
                if(!ac.isAccept())
                    accept=false;
            }
            if (accept)
                orderslist.add(o);
        }
        return orderslist;
    }

    public List<OrderList> getOrderName(){
        return orderListRepository.findAll();
    }

    public List<Order> getWaitingOrder(String name){
        List<Order> orders=orderRepository.findByStudentName(name);
        List<Order> orderslist=new ArrayList<>();
        for (Order o:orders){
            boolean accept=true;
            for(Accept ac:o.getAccepts()){
                if(!ac.isAccept())
                    accept=false;
            }
            if (!accept)
                orderslist.add(o);
        }
        return orderslist;
    }

    @PreAuthorize("hasAeuthority('generate_pdf')")
    public String  generatPDF(long id) {
        Order order = orderRepository.findById(id).get();
        for (Accept a:order.getAccepts()){
            if(!a.isAccept())
                return "Need Accept from "+a.getRoleName();
        }
        switch (order.getOrderId()) {
            case 1:
                Map<String,Object>[] student=restTemplate.getForObject("http://"+gatewayAddress+":"+ gatewayPort +"/register/get?studentname="+order.getStudentName(),Map[].class);

                String fileUrl=restTemplate.postForObject("",null,String.class);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:

                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;
        }
        if (order.getResult()!=null)
        return order.getResult();
        else
            return "Unable to Create PDF";
    }

    public Map<String, Object> addOrder(int oid, MultipartHttpServletRequest request, Principal principal) throws IOException {
        Order order=new Order(oid,principal.getName(),new Date());
        Order o=addOrder(order);
        OrderList orderList=orderListService.find(o.getOrderId());
        o.setOrderList(orderList);
        for (MultipartFile mf:request.getFiles("images")) {
            photoService.savePhoto(o.getId(),mf.getName(),mf);
        }
        Accept accept;
        switch (order.getOrderId()){
            case 1:
                accept=new Accept((int)o.getId(),o.getOrderList().getOrderName(),false);
                acceptService.add(accept);

                break;
            case 2:
                accept=new Accept((int)o.getId(),o.getOrderList().getOrderName(),false);
                acceptService.add(accept);

                break;
            case 3:
                accept=new Accept((int)o.getId(),o.getOrderList().getOrderName(),false);
                acceptService.add(accept);
                break;
            case 4:
                accept=new Accept((int)o.getId(),"ROLE_Aemployee",false);
                acceptService.add(accept);
                accept=new Accept((int)o.getId(),"ROLE_dean",false);
                acceptService.add(accept);

                break;
            case 5:
                accept=new Accept((int)o.getId(),o.getOrderList().getOrderName(),false);
                acceptService.add(accept);
                accept=new Accept((int)o.getId(),o.getOrderList().getOrderName(),false);
                acceptService.add(accept);
                accept=new Accept((int)o.getId(),o.getOrderList().getOrderName(),false);
                acceptService.add(accept);
                break;
            case 6:
                accept=new Accept((int)o.getId(),o.getOrderList().getOrderName(),false);
                acceptService.add(accept);
                accept=new Accept((int)o.getId(),o.getOrderList().getOrderName(),false);
                acceptService.add(accept);
                break;
            case 7:
                accept=new Accept((int)o.getId(),o.getOrderList().getOrderName(),false);
                acceptService.add(accept);
                break;
            default:
                return new HashMap<>();
        }

        Map<String ,Object> map=new HashMap<>();
        map.put("order",addOrder(order));
        return map;

    }
}
