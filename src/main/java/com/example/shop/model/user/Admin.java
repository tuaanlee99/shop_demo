package com.example.shop.model.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "admin")
public class Admin extends User{
    public void setRole(){
        this.setRole("ADMIN");
    }

}
