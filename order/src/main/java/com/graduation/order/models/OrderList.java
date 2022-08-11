package com.graduation.order.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "order_list")
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "order_name")
    String orderName;

    @OneToMany(mappedBy = "orderList", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Order> orders=new HashSet<>();

    public OrderList() {
    }

    public OrderList(String orderName) {
        this.orderName = orderName;
    }

    public OrderList(long id, String orderName) {
        this.id = id;
        this.orderName = orderName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
