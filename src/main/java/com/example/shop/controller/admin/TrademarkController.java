package com.example.shop.controller.admin;

import com.example.shop.model.product.Trademark;
import com.example.shop.service.product.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/trademarks")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class TrademarkController {
    @Autowired
    private TrademarkService trademarkService;

    @GetMapping
    public List<Trademark> findAll(){
        return trademarkService.findAll();
    }
    @GetMapping("/add")
    public String addTrademark(@Param("name") String name){
        return trademarkService.addCategory(name);
    }
    @GetMapping("/{id}/update")
    public String updateCategory(@PathVariable long id, @Param("name") String name){
        return trademarkService.updateCategory(id, name);
    }
    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable long id){
        return trademarkService.deleteCategory(id);
    }
}
