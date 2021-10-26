package com.usa.service;

import com.usa.entities.Motorbike;
import com.usa.repository.MotoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public List<Motorbike> getAll() {
        return motoRepository.findAll();
    }

    public Optional<Motorbike> getMoto(int id) {
        return motoRepository.findById(id);
    }

    public Motorbike save(Motorbike motorbike) {
        if (motorbike.getId() == null) {
            return motoRepository.save(motorbike);
        } else {
            Optional<Motorbike> mot = motoRepository.findById(motorbike.getId());
            if (mot.isEmpty()) {
                return motoRepository.save(motorbike);
            } else {
                return motorbike;
            }
        }
    }

    public Motorbike update(Motorbike motorbike) {
        if (motorbike.getId() != null) {
            Optional<Motorbike> mot = motoRepository.findById(motorbike.getId());
            if (!mot.isEmpty()) {
                if (motorbike.getName() != null) {
                    mot.get().setName(motorbike.getName());
                }
                if (motorbike.getBrand() != null) {
                    mot.get().setBrand(motorbike.getBrand());
                }
                if (motorbike.getYear() != null) {
                    mot.get().setYear(motorbike.getYear());
                }
                if (motorbike.getDescription() != null) {
                    mot.get().setDescription(motorbike.getDescription());
                }
                if (motorbike.getCategory() != null) {
                    mot.get().setCategory(motorbike.getCategory());
                }
                motoRepository.save(mot.get());
                return mot.get();
            } else {
                return motorbike;
            }
        } else {
            return motorbike;
        }

    }

    public boolean deleteMoto(int id) {
        Boolean motoBoolean = motoRepository.findById(id).map(moto -> {
            motoRepository.delete(moto);
            return true;
        }).orElse(false);
        return motoBoolean;
    }
}
