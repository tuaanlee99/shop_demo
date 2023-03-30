package com.example.shop.model.user;

import com.example.shop.model.order.Bill;
import com.example.shop.model.order.Cart;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class Customer extends User{
    @OneToOne(mappedBy = "customer")
    private Cart cart;
    @OneToMany(mappedBy = "customer")
    private List<Bill> bills;

    public void setRole() {
        this.setRole("CUSTOMER");
    }
}
