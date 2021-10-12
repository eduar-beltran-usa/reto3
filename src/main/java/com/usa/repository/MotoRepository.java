package com.usa.repository;

import com.usa.entities.Motorbike;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoRepository extends CrudRepository<Motorbike, Integer> {

    @Override
    List<Motorbike> findAll();
}
