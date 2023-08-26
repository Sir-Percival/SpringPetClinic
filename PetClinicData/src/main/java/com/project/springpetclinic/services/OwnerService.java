package com.project.springpetclinic.services;

import com.project.springpetclinic.models.Owner;

public interface OwnerService extends CrudService<Owner, Long>
{
    Owner findByLastName(String lastName);
}
