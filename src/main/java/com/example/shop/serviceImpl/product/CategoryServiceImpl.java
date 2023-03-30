package com.example.shop.serviceImpl.product;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.model.product.Category;
import com.example.shop.repository.product.CategoryRepository;
import com.example.shop.service.product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Override
    public String addCategory(String name){
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return "thêm Category thành công!";
    }

    @Override
    public String updateCategory(long id,String name){
        Category category = categoryRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("category is exits with id =" +id));
        category.setName(name);
        categoryRepository.save(category);
        return "update Category successfully!";
    }
    @Override
    public String deleteCategory(long id){
        Category category = categoryRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("category is exits with id =" +id));
        categoryRepository.delete(category);
        return "delete Category successfully!";
    }

}
