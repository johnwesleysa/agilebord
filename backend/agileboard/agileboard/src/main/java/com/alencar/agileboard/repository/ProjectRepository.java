package com.alencar.agileboard.repository;


import com.alencar.agileboard.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
    public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
