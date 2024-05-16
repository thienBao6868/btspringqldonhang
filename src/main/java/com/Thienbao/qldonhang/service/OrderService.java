package com.Thienbao.qldonhang.service;

import com.Thienbao.qldonhang.dto.OrderDto;
import com.Thienbao.qldonhang.model.Orders;
import com.Thienbao.qldonhang.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    public ResponseEntity<?> getOrderById(Long id){
        try {
            Orders orders = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found for id: " + id));
            OrderDto orderDto = new OrderDto();

            orderDto.setId(orders.getId());
            orderDto.setOrderDate(orders.getOrderDate());
            orderDto.setDeliveryAddress(orders.getDeliveryAddress());

            orderDto.setListOrderItem(orders.getListOrderItem());

            return new ResponseEntity<>(orderDto,HttpStatus.OK);
        }catch (RuntimeException ex){
            String err = ex.getMessage();
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }
    };
    public ResponseEntity<?> createOrder(Orders order){
        try {
            if(order.getOrderDate() == null){
                throw new RuntimeException("Order Date not null");
            }
            if (order.getDeliveryAddress() == null || order.getDeliveryAddress().isEmpty()){
                throw  new RuntimeException("address not null or empty");
            }
            Orders newOrder = orderRepository.save(order);
            return new ResponseEntity<>(newOrder, HttpStatus.OK);
        }catch (RuntimeException ex){
            String err = ex.getMessage();
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }
    };

    public ResponseEntity<?> updateOrder(Orders order){
        try {
            if(order.getOrderDate() == null){
                throw new RuntimeException("Order Date not null");
            }
            if (order.getDeliveryAddress() == null || order.getDeliveryAddress().isEmpty()){
                throw  new RuntimeException("address not null or empty");
            }
            Orders newOrder = orderRepository.findById(order.getId()).orElseThrow(()-> new RuntimeException("Order not found for id"));
            newOrder.setOrderDate(order.getOrderDate());
            newOrder.setDeliveryAddress(order.getDeliveryAddress());
            orderRepository.save(newOrder);

            return new ResponseEntity<>(newOrder, HttpStatus.OK);
        }catch (RuntimeException ex){
            String err = ex.getMessage();
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteOrder(Long id){
        try {
            orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id"));
            orderRepository.deleteById(id);
            return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        }catch (RuntimeException ex){
            String err = ex.getMessage();
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
    }
}
