package com.example.shop.repository.product;

import com.example.shop.model.product.Category;
import com.example.shop.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select product from Product  product where product.category= (select category from Category category where category.category_id = :id)")
    List<Product> findByCategory(@Param("id")long id);

    @Query("select product from Product  product where product.trademark= (select trademark from Trademark trademark where trademark.trademark_id= :id)")
    List<Product> findByTrademark(@Param("id")long id);

    @Query("select product from Product  product ORDER BY product.name")
    List<Product> findProductByNameOrderUp();

    @Query("select product from Product  product ORDER BY product.name DESC ")
    List<Product> findProductByNameOrderDown();

    @Query("select product from Product product order by product.product_selling")
    List<Product> findByProductBySellingUp();

    @Query("select product from Product product order by product.product_selling desc ")
    List<Product> findProductBySellingDown();

    @Query("select product from Product product order by product.evaluate_arg")
    List<Product>findProductByEvaluate_argUp();

    @Query("select product from Product product order by product.evaluate_arg desc ")
    List<Product>findProductByEvaluate_argDown();

    @Query("select product from Product product where product.name like ?1%")
    List<Product> findProductByName(@Param("name") String name);
}
