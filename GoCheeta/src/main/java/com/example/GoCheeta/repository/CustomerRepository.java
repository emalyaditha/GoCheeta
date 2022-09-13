package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    public long countById(Integer id);

    Optional<Customer> findUserByEmail(String email);

}

