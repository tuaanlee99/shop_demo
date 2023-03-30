package com.example.shop.service.order;

import com.example.shop.model.order.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    List<Bill> findAllBill();

    String addBill(long id);

    String activeBill(long id);

    String disableBill(long id);

    List<Bill> findAllBillACTIVE();

    List<Bill> findAllBillSPENDING();

    List<Bill> findAllBillDisable();
}
