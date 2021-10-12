package com.usa.repository;

import com.usa.entities.Reservation;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    @Override
    List<Reservation> findAll();
}
