package com.oktenwebjava.service;

import com.oktenwebjava.entity.Profession;

import java.util.List;

public interface IProfessionServise {
    Profession saveProfession(Profession profession);

    List<Profession> getallProfessions();

    Profession getProfessionById(int id);

    void deleteProfession(int id);

    Profession updateProfession(int id, Profession profession) throws IllegalAccessException;
}
