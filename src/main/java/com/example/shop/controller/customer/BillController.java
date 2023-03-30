package com.example.shop.controller.customer;

import com.example.shop.service.order.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController("customer-bill")
@RequestMapping
@PreAuthorize("hasAnyAuthority('CUSTOMER')")
public class BillController {
    @Autowired
    private BillService billService;



}
