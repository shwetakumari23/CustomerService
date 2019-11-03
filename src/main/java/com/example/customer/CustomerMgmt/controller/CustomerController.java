package com.example.customer.CustomerMgmt.controller;

import com.example.customer.CustomerMgmt.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    private Map<Integer, Customer> customerMap = new HashMap<Integer, Customer>() ;
    private Integer counter = 1;

    @GetMapping("/api/customer")
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity(customerMap.values(), HttpStatus.OK);
    }

    @GetMapping("/api/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id){
        Customer customer = customerMap.get(id);
        if(customer == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customer, HttpStatus.OK);
    }

    @PostMapping("/api/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        customer.setCustomerId(counter);
        customerMap.put(counter, customer);
        counter++;
        return new ResponseEntity(customer, HttpStatus.OK);

    }
    @PutMapping("/api/customer/{id}")
    public  ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer){
        Customer old = customerMap.get(id);
        old.setAddress(customer.getAddress());
        old.setContactNumber(customer.getContactNumber());
        old.setPassword(customer.getPassword());
        old.setUserName(customer.getUserName());
        old.setName(customer.getName());
        return new ResponseEntity(old, HttpStatus.OK);
    }


}
