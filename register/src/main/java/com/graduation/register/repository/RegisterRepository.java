package com.graduation.register.repository;

import com.graduation.register.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<Register,Long> {
    Optional<Register> findByExamNumber(int exam_number);
    List<Register> findByStdId(int stdId);
 }
