package com.example.lecture.repository;

import com.example.lecture.domain.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    void save(Customer customer);
    void update(Customer customer);
    Customer findById(long id);
    List<Customer> findAll();
}
