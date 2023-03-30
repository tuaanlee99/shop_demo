package com.example.shop.model.order;
import com.example.shop.model.product.ProductOrder;
import com.example.shop.model.user.Customer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bill_id;
    @ManyToOne
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

    @Column(name = "order_date")
    private Date order_date;
    @Column(name = "state")
    private State state;


}
