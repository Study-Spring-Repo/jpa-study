package com.example.lecturejpa.order.service;

import com.example.lecturejpa.domain.order.Order;
import com.example.lecturejpa.domain.order.OrderRepository;
import com.example.lecturejpa.order.converter.OrderConverter;
import com.example.lecturejpa.order.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    /**
     * 1. dto -> entity 변환 (준영속)
     * 2. orderRepository.save(entity) -> 영속화
     * 3. 결과 반환
     */
    @Transactional
    public String save(OrderDto dto) {
        Order order = orderConverter.convertOrder(dto);
        Order entity = orderRepository.save(order);
        return entity.getUuid();
    }

    /**
     * 1. 조회를 위한 키값 인자로 받기
     * 2. orderRepository.findById(uuid) -> 조회 (영속화된 엔티ㅣㅌ)
     * 3. entity -> dto
     */
    @Transactional
    public OrderDto findOne(String uuid) throws ChangeSetPersister.NotFoundException {
        return orderRepository.findById(uuid)
                .map(orderConverter::convertOrderDto)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Transactional
    public Page<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(orderConverter::convertOrderDto);
    }
}
