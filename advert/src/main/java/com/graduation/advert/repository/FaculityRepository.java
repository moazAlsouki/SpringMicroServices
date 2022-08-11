package com.graduation.advert.repository;

import com.graduation.advert.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaculityRepository extends JpaRepository<Faculty,Long> {
    public Optional<Faculty> findByFuri(String furi);
}
