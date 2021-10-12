package com.usa.controller;

import com.usa.entities.Motorbike;
import com.usa.service.MotoService;
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
@RequestMapping("/Motorbike")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @GetMapping("/all")
    public List<Motorbike> getMotos() {
        return motoService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Motorbike> getMoto(@PathVariable("id") int motoId) {
        return motoService.getMoto(motoId);
    }
    
    @PostMapping("/all")
    public Motorbike save(@RequestBody Motorbike motorbike){
        return motoService.save(motorbike);
    }

}
