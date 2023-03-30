package com.example.shop.serviceImpl.order;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.model.order.Bill;
import com.example.shop.model.order.Cart;
import com.example.shop.model.order.State;
import com.example.shop.model.product.Product;
import com.example.shop.model.product.ProductStock;
import com.example.shop.model.user.Customer;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.order.BillRepository;
import com.example.shop.repository.order.CartRepository;
import com.example.shop.repository.product.ProductOrderRepository;
import com.example.shop.repository.product.ProductStockRepository;
import com.example.shop.service.order.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductStockRepository productStockRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Bill> findAllBill(){
        return billRepository.findAll();
    }

    @Override
    public String addBill(long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("customer is not exit with id="+id));
        Cart cart = customer.getCart();
        Bill bill = new Bill();
        bill.setCustomer(customer);
        bill.setProductOrders(cart.getProductOrders());
        long millis=System.currentTimeMillis();
        java.util.Date date=new java.util.Date(millis);
        bill.setOrder_date(date);
        bill.setState(State.PENDING);

        billRepository.save(bill);
        cart.getProductOrders().clear();
        cartRepository.save(cart);
        return "thêm bill thành công!";

    }
    public ProductStock updateStock(Product product, int number, boolean active){
        ProductStock productStock = productStockRepository.findByProduct(product);
        if(active){
            productStock.setNumber(productStock.getNumber()-number);
        } else {
            productStock.setNumber(productStock.getNumber()+number);
        }

        return productStockRepository.save(productStock);
    }

    @Override
    public String activeBill(long id){
        Bill bill = billRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("bill ko tồn tại"));
        bill.setState(State.ACTIVE);
        productOrderRepository.saveAll(bill.getProductOrders());
        List<Product> products =  bill.getProductOrders().stream().map(productOrder -> productOrder.getProduct()).collect(Collectors.toList());
        productStockRepository.saveAll(bill.getProductOrders().stream().map(productOrder ->
                        this.updateStock(productOrder.getProduct(), productOrder.getNumber(), true))
                .collect(Collectors.toList()));
        return "active bill thành công!";
    }

    @Override
    public String disableBill(long id){
        Bill bill = billRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("bill ko tồn tại"));
        if (bill.getState() == State.ACTIVE){
            List<Product> products =  bill.getProductOrders().stream().map(productOrder -> productOrder.getProduct()).collect(Collectors.toList());
            productStockRepository.saveAll(bill.getProductOrders().stream().map(productOrder ->
                            this.updateStock(productOrder.getProduct(), productOrder.getNumber(), false))
                    .collect(Collectors.toList()));
            productOrderRepository.deleteAll(bill.getProductOrders());
        }
        bill.setState(State.DISABLED);
        billRepository.save(bill);
        return "disable bill successfully!";
    }

    @Override
    public List<Bill> findAllBillACTIVE(){
        return billRepository.findBillByState(State.ACTIVE);
    }
    @Override
    public List<Bill> findAllBillSPENDING(){
        return billRepository.findBillByState(State.PENDING);
    }
    @Override
    public List<Bill> findAllBillDisable(){
        return billRepository.findBillByState(State.DISABLED);
    }
}
