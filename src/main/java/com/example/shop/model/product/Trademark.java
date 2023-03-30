package com.example.shop.model.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "trademark")
public class Trademark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trademark_id;
    @NotBlank(message = "thuong hieu name í not blank")
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "trademark")
    private List<Product> products;
}
