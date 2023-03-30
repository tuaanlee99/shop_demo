package com.example.shop.service.order;

import com.example.shop.model.order.Cart;
import com.example.shop.model.product.ProductOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    Cart findByCustomerID(long id);

    String addProductToCart(long id, ProductOrder productOrder);

    String deleteProductsToCart(long id, List<ProductOrder> productOrders_delete);

    String clearProductToCart(long id);
}
