package com.example.GoCheeta.admin;

import com.example.GoCheeta.customer.Customer;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Customer, Integer> {
    public long countById(Integer id);
}
