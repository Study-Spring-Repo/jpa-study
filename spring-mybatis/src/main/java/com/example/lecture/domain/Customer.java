package com.example.lecture.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("customers")

public class Customer {
    private long id;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public Customer(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
