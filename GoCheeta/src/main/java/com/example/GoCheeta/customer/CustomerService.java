package com.example.GoCheeta.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired private CustomerRepository repo;

    public List<Customer>listAll() {return (List<Customer>) repo.findAll();}

    public void save(Customer customer) {
        repo.save(customer);
    }

}
