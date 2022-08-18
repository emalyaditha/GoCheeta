package com.example.GoCheeta.customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    public long countById (Integer id);
}
