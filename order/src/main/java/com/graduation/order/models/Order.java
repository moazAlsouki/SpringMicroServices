package com.graduation.order.models;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "order_id")
    int orderId;
    @Column(name = "student_name")
    String studentName;
    @Column(name = "note")
    @Nullable
    String note;
    @Column(name = "time")
    Date time;
    @Column(name = "result")
    @Nullable
    String result;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<Photo> photos=new HashSet<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<Accept>  accepts=new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, insertable=false, updatable=false)
    OrderList orderList;

    public Order() {
    }

    public Order(int orderId, String studentName, Date time, String note) {
        this.orderId = orderId;
        this.studentName = studentName;
        this.time = time;
        this.note = note;
    }

    public Order(long id, int orderId, String studentName, Date time, String note) {
        this.id = id;
        this.orderId = orderId;
        this.studentName = studentName;
        this.time = time;
        this.note = note;
    }

    public Order(int orderId, String studentName, Date time) {
        this.orderId = orderId;
        this.studentName = studentName;
        this.time = time;
    }

    public Order(long id, int orderId, String studentName, Date time) {
        this.id = id;
        this.orderId = orderId;
        this.studentName = studentName;
        this.time = time;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Accept> getAccepts() {
        return accepts;
    }

    public void setAccepts(Set<Accept> accepts) {
        this.accepts = accepts;
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
