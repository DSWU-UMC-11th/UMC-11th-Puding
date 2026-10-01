package com.umc.study.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.umc.study.repository.RentalRepository;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void createRental(Map<String, Object> body) {
        rentalRepository.save(body);
    }
}
