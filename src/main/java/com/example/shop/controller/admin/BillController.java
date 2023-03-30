package com.example.shop.controller.admin;

import com.example.shop.model.order.Bill;
import com.example.shop.service.order.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController("admin-bills")
@RequestMapping("/admin/bills")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    private List<Bill> findAll(){
        return billService.findAllBill();
    }
    @GetMapping("/bills_spending")
    private List<Bill> findAllSpending(){
        return billService.findAllBillSPENDING();
    }
    @GetMapping("/bills_active")
    private List<Bill> findAllActive(){
        return billService.findAllBillACTIVE();
    }
    @GetMapping("/bills_disable")
    private List<Bill> findAllDisable(){
        return billService.findAllBillDisable();
    }

    @GetMapping("/active_bill/{id}")
    private String activeBill(@PathVariable long id){
        return billService.activeBill(id);
    }
    @GetMapping("/disable_bill/{id}")
    private String disableBill(@PathVariable long id){
        return billService.disableBill(id);
    }
}
