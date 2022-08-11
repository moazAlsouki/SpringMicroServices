package com.graduation.register.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    String name;
    @Column(name = "f_name")
    String fname;
    @Column(name = "m_name")
    String mname;
    @Column(name = "l_name")
    String lname;
    @Column(name = "birth_place")
    String birthPlace;
    @Column(name = "birth")
    Date birth;
    @Column(name = "gender")
    String gender;
    @Column(name = "register")
    String register;
    @Column(name = "recruit")
    String recruit;
    @Column(name = "phone")
    int phone;
    @Column(name = "mobile")
    int mobile;
    @Column(name = "email")
    String email;
    @Column(name = "nationality")
    String nationality;
    @Column(name = "comparison_type")
    String comparison_type;
    @Column(name = "avg")
    double avg;
    @Nullable
    @Column(name = "user_name")
    String userName;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Register> registers=new HashSet<>();


    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Photo> photos=new HashSet<>();
    public Student() {
    }

    public Student(String name, String fname, String mname, String lname, String birthPlace, Date birth, String gender, String register, String recruit, int phone, int mobile, String email, String nationality, String comparison_type, double avg) {
        this.name = name;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.birthPlace = birthPlace;
        this.birth = birth;
        this.gender = gender;
        this.register = register;
        this.recruit = recruit;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.nationality = nationality;
        this.comparison_type = comparison_type;
        this.avg = avg;
    }

    public Student(String name, String fname, String mname, String lname, String birthPlace, Date birth, String gender, String register, String recruit, int phone, int mobile, String email, String nationality, String comparison_type, double avg, String userName) {
        this.name = name;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.birthPlace = birthPlace;
        this.birth = birth;
        this.gender = gender;
        this.register = register;
        this.recruit = recruit;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.nationality = nationality;
        this.comparison_type = comparison_type;
        this.avg = avg;
        this.userName = userName;
    }

    public Student(Long id, String name, String fname, String mname, String lname, String birthPlace, Date birth, String gender, String register, String recruit, int phone, int mobile, String email, String nationality, String comparison_type, double avg, String userName) {
        this.id = id;
        this.name = name;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.birthPlace = birthPlace;
        this.birth = birth;
        this.gender = gender;
        this.register = register;
        this.recruit = recruit;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.nationality = nationality;
        this.comparison_type = comparison_type;
        this.avg = avg;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getRecruit() {
        return recruit;
    }

    public void setRecruit(String recruit) {
        this.recruit = recruit;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getComparison_type() {
        return comparison_type;
    }

    public void setComparison_type(String comparison_type) {
        this.comparison_type = comparison_type;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(Set<Register> registers) {
        this.registers = registers;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
}
