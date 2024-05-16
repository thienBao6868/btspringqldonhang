package com.Thienbao.qldonhang.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    private Long id;

    private String productName;

    private String description;

    private BigDecimal price;

    private int quantity;

    private Long idOrder;

}
