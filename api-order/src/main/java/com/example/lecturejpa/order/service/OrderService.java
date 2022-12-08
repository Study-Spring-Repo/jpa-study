package com.example.lecturejpa.order.service;

import com.example.lecturejpa.domain.order.Order;
import com.example.lecturejpa.domain.order.OrderRepository;
import com.example.lecturejpa.order.converter.OrderConverter;
import com.example.lecturejpa.order.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    /**
     * tx.begin()
     * 1. dto -> entity 변환 (준영속)
     * 2. orderRepository.save(entity) -> 영속화
     * 3. 결과 반환
     * tx.commit()
     */
    @Transactional
    public String save(OrderDto dto) {
        Order order = orderConverter.convertOrder(dto);
        Order entity = orderRepository.save(order);
        return entity.getUuid();
    }

    public void findAll() {

    }

    public void findOne() {

    }
}
