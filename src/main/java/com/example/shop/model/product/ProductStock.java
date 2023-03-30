package com.example.shop.model.product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_stock")
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "number")
    private int number;
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
