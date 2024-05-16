package com.Thienbao.qldonhang.controller;


import com.Thienbao.qldonhang.dto.OrderDto;
import com.Thienbao.qldonhang.model.Orders;
import com.Thienbao.qldonhang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){

        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    };

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody Orders order){
        return new ResponseEntity<>( orderService.createOrder(order), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateOrder(@RequestBody Orders order){
        return new ResponseEntity<>( orderService.updateOrder(order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        return new ResponseEntity<>(orderService.deleteOrder(id),HttpStatus.OK);
    }
}
