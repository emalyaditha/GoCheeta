package com.example.GoCheeta.service;

import com.example.GoCheeta.controller.UserNotFoundException;
import com.example.GoCheeta.model.Customer;
import com.example.GoCheeta.model.Driver;
import com.example.GoCheeta.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private DriverRepository repo;

    public List<Driver> listAll() {
        return (List<Driver>) repo.findAll();
    }

    public void save(Driver driver) {
        repo.save(driver);
    }

    public Driver get(Integer id) throws UserNotFoundException {
        Optional<Driver> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any Driver with ID" + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any Driver with ID:" + id);
        }
        repo.deleteById(id);
    }
}
