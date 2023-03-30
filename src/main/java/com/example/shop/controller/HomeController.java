package com.example.shop.controller;

import com.example.shop.exception.UserException;
import com.example.shop.model.JwtResponse;
import com.example.shop.model.product.Category;
import com.example.shop.model.product.Product;
import com.example.shop.model.product.Trademark;
import com.example.shop.model.user.Admin;
import com.example.shop.model.user.Customer;
import com.example.shop.model.user.User;
import com.example.shop.repository.product.CategoryRepository;
import com.example.shop.repository.product.ProductRepository;
import com.example.shop.repository.product.TrademarkRepository;
import com.example.shop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired

    private ProductRepository productRepository;
    @Autowired

    private TrademarkRepository trademarkRepository;
    @Autowired

    private CategoryRepository categoryRepository;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public String userException( UserException e){
        return e.getMessage();
    }

    @GetMapping("/register")
    ResponseEntity<String> addUser(@Valid @RequestBody Customer userDTO) {
        return ResponseEntity.ok(userService.sign_up(userDTO));
    }
    @GetMapping("/addAdmin")
    ResponseEntity<String> addAdmin(@Valid @RequestBody Admin userDTO) {
        return ResponseEntity.ok(userService.addAdmin(userDTO));
    }

    @GetMapping("/log-in")
    JwtResponse log_in(@Param("username") String username, @Param("password") String password){
        return userService.log_in(username, password);
    }
    @GetMapping("/products")
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @GetMapping("/trademarks")
    public List<Trademark> findAllTrademark(){
        return trademarkRepository.findAll();
    }
    @GetMapping("/categories")
    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }
    @GetMapping("/category_id = {id}/products")
    public List<Product> findProductsByCategory(@PathVariable long id){
        return productRepository.findByCategory(id);
    }
    @GetMapping("/trademark_id = {id}/products")
    public List<Product> findProductsByTrademark(@PathVariable long id){
        return productRepository.findByTrademark(id);
    }
    @GetMapping("/products/order_up")
    public List<Product> findProductByNameOrderUp(){
        return productRepository.findProductByNameOrderUp();
    }
    @GetMapping("/products/order_down")
    public List<Product>  findProductByNameOrderDown(){
        return productRepository.findProductByNameOrderDown();
    }

    @GetMapping("/product_id = {id}")
    public Optional<Product> findProductById(@PathVariable long id){
        return productRepository.findById(id);
    }

    @GetMapping("/products/evaluate_ard_up")
    public List<Product> findProductByEvaluate_argUp(){
        return productRepository.findProductByEvaluate_argUp();
    }
    @GetMapping("/products/evaluate_ard_down")
    public List<Product> findProductByEvaluate_argDown(){
        return productRepository.findProductByEvaluate_argDown();
    }

    @GetMapping("/products/selling_up")
    public List<Product> findByProductBySellingUp(){
        return productRepository.findByProductBySellingUp();
    }

    @GetMapping("/products/selling_down")
    public List<Product> findByProductBySellingDown(){
        return productRepository.findProductBySellingDown();
    }
    @GetMapping("/products/search")
    public List<Product> findProductByName(@Param("name") String name){
        return productRepository.findProductByName(name);
    }

}
