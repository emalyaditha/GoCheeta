package com.example.GoCheeta.repository;

import com.example.GoCheeta.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends CrudRepository<Booking, Integer> {

    public long countById(Integer id);
}
