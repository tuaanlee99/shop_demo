package com.example.shop.service.product;

import com.example.shop.model.product.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> findAll();

    String addCategory(String name);

    String updateCategory(long id, String name);

    String deleteCategory(long id);
}
