package com.usa.service;

import com.usa.entities.CountClients;
import com.usa.entities.Reservation;
import com.usa.entities.StatusAmount;
import com.usa.repository.ReservationReportRepository;
import com.usa.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Grupo11
 */
@Service
public class ReservationService {

    /** Injection ReservationRepository*/
    @Autowired
    private ReservationRepository reservationRepository;

    /** Injection ReservationReportRepository*/
    @Autowired
    private ReservationReportRepository reservationReportRepository;

    /**
     *
     * @return List
     */
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    /**
     *
     * @param id
     * @return Object
     */
    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.findById(id);
    }

    /**
     *
     * @param reservation
     * @return response
     */
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

    /**
     *
     * @param reservation
     * @return response
     */
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reserv = reservationRepository.findById(reservation.getIdReservation());
            if (!reserv.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    reserv.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    reserv.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    reserv.get().setStatus(reservation.getStatus());
                }
                if (reservation.getClient() != null) {
                    reserv.get().setClient(reservation.getClient());
                }
                if (reservation.getMotorbike() != null) {
                    reserv.get().setMotorbike(reservation.getMotorbike());
                }
                reservationRepository.save(reserv.get());
                return reserv.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }

    }

    /**
     *
     * @param id
     * @return boolean
     */
    public boolean deleteReservation(int id) {
        Boolean reservBoolean = reservationRepository.findById(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return reservBoolean;
    }

    /**
     *
     * @return response
     */
    public List<CountClients> getTopCategorias() {
        return reservationReportRepository.getTopClients();
    }

    /**
     *
     * @return Object
     */
    public StatusAmount getStatusReport() {
        List<Reservation> completed = reservationReportRepository.getReservationByStatus("completed");
        List<Reservation> cancelled = reservationReportRepository.getReservationByStatus("cancelled");

        StatusAmount descAmt = new StatusAmount(completed.size(), cancelled.size());
        return descAmt;
    }

    /**
     *
     * @param date1
     * @param date2
     * @return List
     */
    public List<Reservation> getReservationPeriod(String date1, String date2) {

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();
        try {
            dateOne = parser.parse(date1);
            dateTwo = parser.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dateOne.before(dateTwo)) {
            return reservationRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
        } else {
            return new ArrayList<>();
        }
    }
}
