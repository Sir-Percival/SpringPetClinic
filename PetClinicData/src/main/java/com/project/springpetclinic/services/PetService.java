package com.project.springpetclinic.services;

import com.project.springpetclinic.models.Pet;

import java.util.Set;

public interface PetService
{
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
