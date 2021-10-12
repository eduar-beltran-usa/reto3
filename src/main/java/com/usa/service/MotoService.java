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

    public Motorbike update(Motorbike moto) {
        if (moto.getId() != null) {
            Optional<Motorbike> mot = motoRepository.findById(moto.getId());
            if (!mot.isEmpty()) {
                if (moto.getName() != null) {
                    mot.get().setName(moto.getName());
                }
                if (moto.getBrand() != null) {
                    mot.get().setBrand(moto.getBrand());
                }
                if (moto.getYear() != null) {
                    mot.get().setYear(moto.getYear());
                }
                if (moto.getDescription() != null) {
                    mot.get().setDescription(moto.getDescription());
                }
                if (moto.getCategory() != null) {
                    mot.get().setCategory(moto.getCategory());
                }
                motoRepository.save(mot.get());
                return mot.get();
            } else {
                return moto;
            }
        } else {
            return moto;
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
