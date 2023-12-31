package com.project.springpetclinic.services.map;

import com.project.springpetclinic.models.Owner;
import com.project.springpetclinic.models.Pet;
import com.project.springpetclinic.services.OwnerService;
import com.project.springpetclinic.services.PetService;
import com.project.springpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService
{
    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {

        if(object != null)
        {
            if(object.getPets() != null)
            {
                object.getPets().forEach(pet ->
                {
                    if(pet.getPetType() != null)
                    {
                        if(pet.getPetType().getId() == null)
                        {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }
                    else
                    {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pet.getId() == null)
                    {
                        Pet savedPed = petService.save(pet);
                        pet.setId(savedPed.getId());
                    }
                });
            }

            return super.save(object);
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
