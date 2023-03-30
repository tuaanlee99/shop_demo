package com.example.shop.serviceImpl.product;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.model.product.Product;
import com.example.shop.repository.product.ProductRepository;
import com.example.shop.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAllProduct(){
        return productRepository.findAll();

    }
    @Override
    public String addProduct(@Valid Product product){
        productRepository.save(product);
        return "Thêm product thành công!";
    }
    @Override
    public Product findByID(long id){
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product không tồn tại với id" +id));
    }
    @Override
    public String updateProduct(long id, Product productDTO){
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product không tồn tại với id" +id));
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setTrademark(productDTO.getTrademark());
        product.setPrice(productDTO.getPrice());
        product.setSale(productDTO.getSale());
        product.setProduct_selling();
        product.setEvaluate_arg(0.0);

        productRepository.save(product);
        return "Update product thành công!";
    }
    @Override
    public String deleteProduct(long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product không tồn tại với id" +id));
        productRepository.delete(product);
        return "Xóa product thành công!";
    }
}
