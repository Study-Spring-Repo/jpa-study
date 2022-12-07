package com.example.lecturejpa.domain.order;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Furniture extends Item{
    private int width;
    private int height;
}
