package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface LoginRepository extends JpaRepository<Customer, Long> {
        Customer findByEmailAndPassword(String email, String password);

        Customer findUserByEmail(String email);
}
