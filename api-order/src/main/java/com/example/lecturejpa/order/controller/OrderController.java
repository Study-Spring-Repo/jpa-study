package com.example.lecturejpa.order.controller;

import com.example.lecturejpa.order.ApiResponse;
import com.example.lecturejpa.order.dto.OrderDto;
import com.example.lecturejpa.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ApiResponse<String> notFoundHandler (ChangeSetPersister.NotFoundException e) {
        return ApiResponse.fail(404, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<String> internalServerErrorHandler (Exception e) {
        return ApiResponse.fail(500, e.getMessage());
    }

    @PostMapping("/orders")
    public ApiResponse<String> save(
            @RequestBody OrderDto orderDto
    ) {
        String uuid = orderService.save(orderDto);
        return ApiResponse.ok(uuid);
    }

    @GetMapping("/orders/{uuid}")
    public ApiResponse<OrderDto> getOne(
            @PathVariable String uuid
    ) throws ChangeSetPersister.NotFoundException {
        OrderDto one = orderService.findOne(uuid);
        return ApiResponse.ok(one);
    }

    @GetMapping("/orders")
    public ApiResponse<Page<OrderDto>> getAll(
            Pageable pageable
    ) {
        Page<OrderDto> all = orderService.findAll(pageable);
        return ApiResponse.ok(all);
    }
}
