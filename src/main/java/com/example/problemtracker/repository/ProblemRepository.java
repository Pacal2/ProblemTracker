package com.example.problemtracker.repository;

import com.example.problemtracker.structure.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

}
