package com.project.springpetclinic.repositories;

import com.project.springpetclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long>
{
    Optional<Owner> findByLastName(String lastName);
}
