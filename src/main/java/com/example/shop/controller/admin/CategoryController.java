package com.example.shop.controller.admin;

import com.example.shop.model.product.Category;
import com.example.shop.service.product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/categories")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }
    @GetMapping("/add")
    public String addCategory(@Param("name") String name){
        return categoryService.addCategory(name);
    }
    @GetMapping("/{id}/update")
    public String updateCategory(@PathVariable long id,@Param("name") String name){
        return categoryService.updateCategory(id, name);
    }
    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable long id){
        return categoryService.deleteCategory(id);
    }

}
