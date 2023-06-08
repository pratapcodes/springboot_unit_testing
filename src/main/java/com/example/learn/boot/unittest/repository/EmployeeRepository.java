package com.example.learn.boot.unittest.repository;

import com.example.learn.boot.unittest.domain.PersistentEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<PersistentEmployeeEntity,Long> {

}
