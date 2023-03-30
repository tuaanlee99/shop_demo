package com.example.shop.model.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;
    @NotBlank(message = "danh muc name is not blank")
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
