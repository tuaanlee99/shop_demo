package com.example.shop.model.order;

import com.example.shop.model.product.ProductOrder;
import com.example.shop.model.user.Customer;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cart_id;

    @OneToOne
    @JoinColumn(name = "customer_user_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<ProductOrder> productOrders;

    @Column(name = "total_money")
    private long total_money;

    public void setTotal_money() {
        long total_money = 0;
        for (ProductOrder productOrder: productOrders){
            total_money+=productOrder.getProduct().getPrice()*productOrder.getNumber();
        }
        this.total_money = total_money;
    }

}
