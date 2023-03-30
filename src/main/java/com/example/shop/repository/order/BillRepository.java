package com.example.shop.repository.order;

import com.example.shop.model.order.Bill;
import com.example.shop.model.order.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query("select bill from Bill bill where bill.state = :state")
    List<Bill> findBillByState(State state);
}
