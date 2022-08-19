package com.example.GoCheeta.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface LoginRepository extends JpaRepository<Customer, Long> {
        Customer findByEmailAndPassword(String email, String password);
}
