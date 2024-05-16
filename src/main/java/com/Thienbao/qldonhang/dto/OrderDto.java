package com.Thienbao.qldonhang.dto;
import com.Thienbao.qldonhang.model.OrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Date orderDate;
    private String deliveryAddress;
   private List<OrderItem> listOrderItem;
}
