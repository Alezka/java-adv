package com.oktenwebjava.controller;

import com.oktenwebjava.entity.Profession;
import com.oktenwebjava.service.IProfessionServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/professions")
public class ProfessionController {
    @Autowired
    private IProfessionServise professionServise;//автовайредься завжди інтерфейси а не класи

    @GetMapping
    public List<Profession> getAllProfessions() {
        return professionServise.getallProfessions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profession createProfession(@RequestBody Profession profession) {
        log.info("Handled POST request with body: {}", profession);
        return professionServise.saveProfession(profession);
    }

    @GetMapping("/profession")
    public Profession getById(@PathVariable int id) {
        return professionServise.getProfessionById(id);
    }

    @PutMapping("/{id}")
    public Profession updateById(@PathVariable int id,@RequestBody @Valid Profession newProfession) throws IllegalAccessException {
        return professionServise.updateProfession(id, newProfession);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") int id) {
        professionServise.deleteProfession(id);
    }
}
