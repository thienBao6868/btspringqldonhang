package com.Thienbao.qldonhang.service;


import com.Thienbao.qldonhang.dto.OrderItemDto;
import com.Thienbao.qldonhang.model.OrderItem;
import com.Thienbao.qldonhang.model.Orders;
import com.Thienbao.qldonhang.repository.OrderItemRepository;
import com.Thienbao.qldonhang.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<?> getAllOrderItem() {
        return new ResponseEntity<>(orderItemRepository.findAll(), HttpStatus.OK);
    }

    ;

    public ResponseEntity<?> createOrderItem(OrderItemDto orderItem) {
        try {
            if (orderItem.getProductName() == null || orderItem.getProductName().isEmpty()) {
                throw new RuntimeException("product name not null or empty");
            }
            if (orderItem.getDescription() == null || orderItem.getDescription().isEmpty())
                throw new RuntimeException("product description not null or empty");
            if (orderItem.getPrice() == null) throw new RuntimeException("price not null");
            if (orderItem.getPrice().compareTo(BigDecimal.ZERO) <= 0)
                throw new RuntimeException("Price must be greater than 0");
            if (orderItem.getQuantity() < 0) throw new RuntimeException("quantity must be greate than 0");

            Orders order = orderRepository.findById(orderItem.getIdOrder()).orElseThrow(() -> new RuntimeException("Not found with id Order"));

            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setProductName(orderItem.getProductName());
            newOrderItem.setDescription(orderItem.getDescription());
            newOrderItem.setQuantity(orderItem.getQuantity());
            newOrderItem.setPrice(orderItem.getPrice());

            newOrderItem.setOrder(order);

            orderItemRepository.save(newOrderItem);
            return new ResponseEntity<>(newOrderItem, HttpStatus.OK);

        } catch (RuntimeException ex) {
            String err = ex.getMessage();
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
    }

    ;

    public ResponseEntity<?> updateOrderItem(OrderItemDto orderItemDto) {
        try {
            if (orderItemDto.getProductName() == null || orderItemDto.getProductName().isEmpty())
                throw new RuntimeException("product name not null or empty");
            if (orderItemDto.getDescription() == null || orderItemDto.getDescription().isEmpty())
                throw new RuntimeException("product description not null or empty");
            if (orderItemDto.getPrice() == null) throw new RuntimeException("price not null");
            if (orderItemDto.getPrice().compareTo(BigDecimal.ZERO) <= 0)
                throw new RuntimeException("Price must be greater than 0");
            if (orderItemDto.getQuantity() < 0) throw new RuntimeException("quantity must be greate than 0");

            OrderItem orderItem = orderItemRepository.findById(orderItemDto.getId()).orElseThrow(() -> new RuntimeException("Not found with id OrderItem"));

            Orders order = orderRepository.findById(orderItemDto.getIdOrder()).orElseThrow(() -> new RuntimeException("Not found with id Order"));


            orderItem.setProductName(orderItemDto.getProductName());
            orderItem.setDescription(orderItemDto.getDescription());
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setPrice(orderItemDto.getPrice());

            orderItem.setOrder(order);

            orderItemRepository.save(orderItem);
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        } catch (RuntimeException ex) {
            String err = ex.getMessage();
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
    }

    ;


    public ResponseEntity<?> deleteOrderItem(Long id) {
        try {
            orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Item not found with id"));
            orderItemRepository.deleteById(id);
            return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        } catch (RuntimeException ex) {
            String err = ex.getMessage();
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

    }

    ;


}

