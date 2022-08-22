package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Integer> {
    public long countById(Integer id);
}
