package com.example.lecturejpa.domain.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_item")
@Getter @Setter
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int price;
    private int quantity;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "item_id")
    private Long itemId;

    @OneToMany(mappedBy = "orderItem")
    private List<Item> items = new ArrayList<>();
}
