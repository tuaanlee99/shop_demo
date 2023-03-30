package com.example.shop.model.product;

import com.example.shop.model.order.Bill;
import com.example.shop.model.order.Cart;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_order")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "number")
    private int number;
}
