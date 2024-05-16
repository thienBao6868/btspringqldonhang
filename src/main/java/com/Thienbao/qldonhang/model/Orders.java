package com.Thienbao.qldonhang.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity(name="orders")
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_date")
    private Date orderDate;

    @Column(name="delivery_address")
    private String deliveryAddress;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> listOrderItem;


}
