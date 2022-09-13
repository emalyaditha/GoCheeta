package com.example.GoCheeta.service;

import com.example.GoCheeta.controller.UserNotFoundException;
import com.example.GoCheeta.model.Customer;
import com.example.GoCheeta.repository.LoginRepository;
import com.example.GoCheeta.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Customer name(String email) {
        Customer user = repo1.findUserByEmail(email);
        return user;
    }
    public Customer get(Integer id) throws UserNotFoundException {
        Optional<Customer> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID" + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID:" + id);
        }
        repo.deleteById(id);
    }
}

