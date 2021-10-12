package com.usa.controller;

import com.usa.entities.Reservation;
import com.usa.service.ReservationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {
    
    
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getMotos() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getMoto(@PathVariable("id") int reservationId) {
        return reservationService.getReservation(reservationId);
    }
    
    @PostMapping("/save")
    public Reservation save(@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }

}
