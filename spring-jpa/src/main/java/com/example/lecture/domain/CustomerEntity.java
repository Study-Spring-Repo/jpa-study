package com.example.lecture.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter @Setter
public class CustomerEntity {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private int age;
}
