package com.example.shop.serviceImpl.product;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.model.product.Trademark;
import com.example.shop.repository.product.TrademarkRepository;
import com.example.shop.service.product.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class TrademarkServiceImpl implements TrademarkService {
    @Autowired
    private TrademarkRepository trademarkRepository;

    @Override
    public List<Trademark> findAll(){
        return trademarkRepository.findAll();
    }

    @Override
    public String addCategory(String name){
        Trademark trademark = new Trademark();
        trademark.setName(name);
        trademarkRepository.save(trademark);
        return "add Trademark successfully!";
    }

    @Override
    public String updateCategory(long id,String name){
       Trademark trademark = trademarkRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("category is exits with id =" +id));
        trademark.setName(name);
        trademarkRepository.save(trademark);
        return "update Trademark successfully!";
    }
    @Override
    public String deleteCategory(long id){
        Trademark trademark = trademarkRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("category is exits with id =" +id));
        trademarkRepository.delete(trademark);
        return "delete Trademark successfully!";
    }
}
