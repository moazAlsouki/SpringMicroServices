package com.graduation.mark.repository;

import com.graduation.mark.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    public List<Exam> findByYear(int year);
}
