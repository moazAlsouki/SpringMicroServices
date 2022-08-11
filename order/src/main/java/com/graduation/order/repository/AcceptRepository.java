package com.graduation.order.repository;

import com.graduation.order.models.Accept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptRepository extends JpaRepository<Accept,Long> {
    List<Accept> findByRoleName(String roleName);
    List<Accept> findByOrderId(int orderId);
}
