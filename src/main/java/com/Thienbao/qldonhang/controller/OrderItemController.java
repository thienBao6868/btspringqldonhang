package com.Thienbao.qldonhang.controller;


import com.Thienbao.qldonhang.dto.OrderItemDto;
import com.Thienbao.qldonhang.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("")
    public ResponseEntity<?> getAllOrderItem(){
        return  new ResponseEntity<>(orderItemService.getAllOrderItem(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createOrderItem(@RequestBody OrderItemDto orderItem){
        return new ResponseEntity<>(orderItemService.createOrderItem(orderItem),HttpStatus.OK);
    };
    @PutMapping("")
    public ResponseEntity<?> updateOrderItem(@RequestBody OrderItemDto orderItem){
        return new ResponseEntity<>(orderItemService.updateOrderItem(orderItem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long id){
        return new ResponseEntity<>(orderItemService.deleteOrderItem(id), HttpStatus.OK);
    }

}
