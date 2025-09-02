package com.alencar.agileboard.repository;


import com.alencar.agileboard.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface ProjectRepository extends JpaRepository<Project, Long> {
}
