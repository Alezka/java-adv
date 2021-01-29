package com.oktenwebjava.service;

import com.oktenwebjava.dao.ProfessionRepository;
import com.oktenwebjava.entity.Profession;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService implements IProfessionServise {

    private ProfessionRepository professionRepository;

    @Autowired
    public ProfessionService(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    @Override
    public Profession saveProfession(Profession profession) {
        return professionRepository.saveAndFlush(profession);
    }

    @Override
    public List<Profession> getallProfessions() {
        return professionRepository.findAll();
    }

    @Override
    @Async
    public Profession getProfessionById(int id) {
        return professionRepository.getOne(id);
    }

    @Override
    public void deleteProfession(int id) {
        professionRepository.deleteById(id);
    }

    @Override
    public Profession updateProfession(int id, Profession profession) throws IllegalAccessException {
        if (professionRepository.existsById(id)) {
            profession.setId(id);
            return professionRepository.saveAndFlush(profession);
        } else {
            throw new IllegalAccessException("No profession is such id" + id);
        }
    }
}
