package com.example.shop.controller.customer;

import com.example.shop.model.order.Cart;
import com.example.shop.model.product.ProductOrder;
import com.example.shop.service.user.CustomerService;
import com.example.shop.service.order.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController("customer-cart")
@RequestMapping
@PreAuthorize("hasAnyAuthority('CUSTOMER')")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{id}/cart")
    public Cart findByUser(@PathVariable long id){
        return cartService.findByCustomerID(id);
    }
    @GetMapping("/{id}/add_product")
    public String addProductToCart(@PathVariable long id, @Param("product_order")ProductOrder productOrder){
        return cartService.addProductToCart(id, productOrder);
    }
    @GetMapping("/{id}/deleteProducts")
    public String deleteProductFromCart(@PathVariable long id, @Param("product_orders") List<ProductOrder> productOrders_delete){
        return cartService.deleteProductsToCart(id, productOrders_delete);
    }
    @GetMapping("{id}/clearProducts")
    public String clearProductFromCart(@PathVariable long id){
        return cartService.clearProductToCart(id);
    }
}
