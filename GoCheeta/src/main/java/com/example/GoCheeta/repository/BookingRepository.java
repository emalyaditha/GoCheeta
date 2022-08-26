package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Booking;
import com.example.GoCheeta.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends CrudRepository<Booking, Integer> {

    public long countById(Integer id);
}
