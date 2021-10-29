package com.usa.repository;

import com.usa.entities.Client;
import com.usa.entities.CountClients;
import com.usa.entities.Reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationReportRepository {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getReservationByStatus(String status) {
        return reservationRepository.findAllByStatus(status);
    }

    public List<Reservation> getReservationPeriod(Date dateOne, Date dateTwo) {
        return reservationRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }
    public List<CountClients> getTopClients() {
        List<CountClients> resp = new ArrayList<>();
        
        List<Object[]> reportClient = reservationRepository.countTotalReservationByClients();
        for (int i = 0; i < reportClient.size(); i++) {
            /*Client client = (Client) reportClient.get(i)[0];
            Integer quantity=(Integer) reportClient.get(i)[1];
            CountClients countClients new CountClients(quantity, client);
            resp.add(countClients);*/
            resp.add(new CountClients((Long) reportClient.get(i)[1], (Client) reportClient.get(i)[0]));
        }
        return resp;
    }
}
