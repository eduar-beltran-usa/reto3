package com.usa.service;

import com.usa.entities.Reservation;
import com.usa.repository.ReservationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reserv = reservationRepository.findById(reservation.getIdReservation());
            if (reserv.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation()!= null) {
            Optional<Reservation> mess = reservationRepository.findById(reservation.getIdReservation());
            if (!mess.isEmpty()) {
                if (reservation.getStartDate()!= null) {
                    mess.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate()!= null) {
                    mess.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getClient() != null) {
                    mess.get().setClient(reservation.getClient());
                }
                if (reservation.getMotorbike() != null) {
                    mess.get().setMotorbike(reservation.getMotorbike());
                }
                reservationRepository.save(mess.get());
                return mess.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }

    }

    public boolean deleteReservation(int id) {
        Boolean motoBoolean = reservationRepository.findById(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return motoBoolean;
    }
}
