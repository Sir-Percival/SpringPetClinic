package com.project.springpetclinic.services;

import com.project.springpetclinic.models.Vet;

import java.util.Set;

public interface VetService
{
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
