package com.program.orderservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "t_order_line_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id") // Name of the foreign key column in t_order_line_items table
    private Order order;

}
