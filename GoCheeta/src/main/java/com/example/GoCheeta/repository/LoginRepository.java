package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface LoginRepository extends JpaRepository<Users, Long> {
        Users findByEmailAndPassword(String email, String password);

        Users findUserByEmail(String email);
}
