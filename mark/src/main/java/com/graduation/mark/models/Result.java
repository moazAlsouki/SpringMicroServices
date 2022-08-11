package com.graduation.mark.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_id")
    int studentid;
    @Column(name = "examId")
    int examId;
    @Column(name = "mark")
    int mark;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "examId", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    Exam exam;

    public Result() {
    }

    public Result(Long id, int studentid, int mark, int examId) {
        this.id = id;
        this.studentid = studentid;
        this.mark = mark;
        this.examId = examId;
    }

    public Result(int studentid, int mark, int examId) {
        this.studentid = studentid;
        this.mark = mark;
        this.examId = examId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", studentid=" + studentid +
                ", mark=" + mark +
                ", examId=" + examId +
                '}';
    }
}
