package com.example.problemtracker.repository;

import com.example.problemtracker.structure.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>{

}
