package com.example.GoCheeta.customer;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;
    @Autowired
    private LoginRepository repo1;

    public List<Customer> listAll() {
        return (List<Customer>) repo.findAll();
    }

    public void save(Customer customer) {
        repo.save(customer);
    }

    public Customer log(String email, String password) {
        Customer user = repo1.findByEmailAndPassword(email, password);
        return user;
    }

}

