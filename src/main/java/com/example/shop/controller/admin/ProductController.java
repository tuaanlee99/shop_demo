package com.example.shop.controller.admin;

import com.example.shop.model.product.Product;
import com.example.shop.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController("admin-products")
@RequestMapping("/admin/products")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll(){
        return productService.findAllProduct();
    }
    @GetMapping("/add")
    public String addProduct(@RequestBody Product productDTO){
        return productService.addProduct(productDTO);
    }
    @GetMapping("/{id}/update")
    public String updateProduct(@PathVariable long id, Product productDTO){
        return productService.updateProduct(id, productDTO);
    }
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable long id){
        return productService.deleteProduct(id);
    }


}
