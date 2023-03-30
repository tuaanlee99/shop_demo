package com.example.shop.serviceImpl.order;

import com.example.shop.model.order.Cart;
import com.example.shop.model.product.ProductOrder;
import com.example.shop.model.user.Customer;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.order.CartRepository;
import com.example.shop.service.order.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Cart findByCustomerID(long id){
        Customer customer = customerRepository.findById(id).get();
        return customer.getCart();
    }

    @Override
    public String addProductToCart(long id, ProductOrder productOrder){
        Customer customer = customerRepository.findById(id).get();
        Cart cart = customer.getCart();
        List<ProductOrder> productOrders = cart.getProductOrders();
        productOrders.add(productOrder);
        cart.setProductOrders(productOrders);
        cartRepository.save(cart);
        return "Add product to cart successfully!";
    }

    @Override
    public String deleteProductsToCart(long id, List<ProductOrder> productOrders_delete){
        Customer customer = customerRepository.findById(id).get();
        Cart cart = customer.getCart();
        List<ProductOrder> productOrders = cart.getProductOrders();
        for (ProductOrder productOrder: productOrders_delete){
            productOrders.remove(productOrder);
        }
        cart.setProductOrders(productOrders);
        cartRepository.save(cart);
        return "Delete products to cart successfully!";
    }

    @Override
    public String clearProductToCart(long id){
        Customer customer = customerRepository.findById(id).get();
        Cart cart = customer.getCart();
        List<ProductOrder> productOrders = cart.getProductOrders();
        productOrders.clear();
        cart.setProductOrders(productOrders);
        cartRepository.save(cart);
        return "Clear product to cart successfully!";
    }
}
