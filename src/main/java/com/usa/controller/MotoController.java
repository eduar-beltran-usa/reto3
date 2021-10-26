package com.usa.controller;

import com.usa.entities.Motorbike;
import com.usa.service.MotoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Motorbike")
@CrossOrigin(origins = "*", 
             methods = {RequestMethod.GET,
                        RequestMethod.POST,
                        RequestMethod.PUT,
                        RequestMethod.DELETE})
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
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Motorbike save(@RequestBody Motorbike motorbike){
        return motoService.save(motorbike);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Motorbike update(@RequestBody Motorbike motorbike) {
        return motoService.update(motorbike);
    }
    
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int motoId) {
        return motoService.deleteMoto(motoId);
    }

}
