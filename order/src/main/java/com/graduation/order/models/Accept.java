package com.graduation.order.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accepts")
public class Accept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "order_id")
    int orderId;
    @Column(name = "role_name")
    String roleName;
    @Column(name = "is_accept")
    boolean isAccept;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    Order order;

    public Accept() {
    }

    public Accept(int orderId, String roleName, boolean isAccept) {
        this.orderId = orderId;
        this.roleName = roleName;
        this.isAccept = isAccept;
    }

    public Accept(long id, int orderId, String roleName, boolean isAccept) {
        this.id = id;
        this.orderId = orderId;
        this.roleName = roleName;
        this.isAccept = isAccept;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public void setAccept(boolean accept) {
        isAccept = accept;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

