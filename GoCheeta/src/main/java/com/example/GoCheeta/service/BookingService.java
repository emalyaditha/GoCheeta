package com.example.GoCheeta.service;

import com.example.GoCheeta.model.Booking;
import com.example.GoCheeta.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;


    public List<Booking> listAllB() { return (List<Booking>) repo.findAll(); }

//    public void save(Booking booking) {repo.save(booking);}

}
