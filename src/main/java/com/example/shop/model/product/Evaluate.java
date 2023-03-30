package com.example.shop.model.product;

import com.example.shop.model.user.Customer;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "evaluate")
public class Evaluate {

    @Id
    @GeneratedValue()
    private long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;

    @Column(name = "stateEvaluate")
    private StateEvaluate stateEvaluate;
}
