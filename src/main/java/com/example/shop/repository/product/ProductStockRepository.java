package com.example.shop.repository.product;

import com.example.shop.model.product.Product;
import com.example.shop.model.product.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
    @Query("select product_stock from ProductStock product_stock where product_stock.product= :product")
    ProductStock findByProduct(@Param("product") Product product);
}
