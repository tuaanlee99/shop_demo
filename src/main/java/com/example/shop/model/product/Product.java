package com.example.shop.model.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;
    @NotNull(message = "product name is not null")
    @Column(name = "name")
    private String name;
    @NotNull(message = "gia product is not null")
    @Column(name = "price")
    private long price;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "trademark_id", nullable = false )
    private Trademark trademark;
    @Column(name = "sale")
    private double sale;
    @OneToOne(mappedBy = "product")
    private ProductStock productStock;

    @OneToMany(mappedBy = "product")
    private List<ProductOrder> productOrders;

    @Column(name = "product_selling")
    private long product_selling;

    public void setProduct_selling() {
        long product_selling = 0;
        for (ProductOrder productOrder: this.productOrders){
            product_selling+=productOrder.getNumber();
        }
        this.product_selling = product_selling;
    }

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    @OneToMany(mappedBy = "product")
    private List<Evaluate> evaluates;

    @Column(name = "evaluate_arg")
    private Double evaluate_arg;

    private void setEvaluate_arg(){
        long evaluate_sum = 0;
        Double evaluate_arg = 0.0;
        if (this.evaluates != null){
            for (Evaluate evaluate: this.evaluates){
                evaluate_sum += evaluate.getStateEvaluate().stateEvaluate;
            }
            evaluate_arg =(double) evaluate_arg/this.evaluates.size();
        }
        this.evaluate_arg = evaluate_arg;
    }

}
