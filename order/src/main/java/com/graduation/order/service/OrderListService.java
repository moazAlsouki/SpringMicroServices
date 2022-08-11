package com.graduation.order.service;

import com.graduation.order.models.OrderList;
import com.graduation.order.repository.OrderListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderListService {
    @Autowired
    OrderListRepository orderListRepository;


    public OrderList add(OrderList orderList){
        return orderListRepository.save(orderList);
    }

    public OrderList find(long id){
        return orderListRepository.findById(id).get();
    }



}
