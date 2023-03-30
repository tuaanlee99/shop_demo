package com.example.shop.service.product;

import com.example.shop.model.product.Product;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public interface ProductService {
    List<Product> findAllProduct();

    String addProduct(@Valid Product product);

    Product findByID(long id);

    String updateProduct(long id, Product productDTO);

    String deleteProduct(long id);
}
