package com.example.lecture.repository;

import com.example.lecture.domain.Customer;
import com.example.lecture.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {


}
