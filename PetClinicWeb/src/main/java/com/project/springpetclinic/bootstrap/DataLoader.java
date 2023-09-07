package com.project.springpetclinic.bootstrap;

import com.project.springpetclinic.models.*;
import com.project.springpetclinic.services.OwnerService;
import com.project.springpetclinic.services.PetTypeService;
import com.project.springpetclinic.services.SpecialityService;
import com.project.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        int count = petTypeService.findAll().size();

        if (count == 0)
        {
            loadData();
        }
    }

    private void loadData() {
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        PetType dog = new PetType();
        cat.setName("Cat");
        PetType savedDogPetType = petTypeService.save(dog);

        System.out.println("Load pet types.....");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("Baker Street 221b");
        owner1.setCity("London");
        owner1.setTelephone("123123123");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedCatPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDay(LocalDate.now());
        mikesPet.setName("KittyKat");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("Kings Road 12");
        owner2.setCity("London");
        owner2.setTelephone("987987987");

        Pet fionasDog = new Pet();
        fionasDog.setPetType(savedDogPetType);
        fionasDog.setOwner(owner2);
        fionasDog.setBirthDay(LocalDate.now());
        fionasDog.setName("Brutus");
        owner2.getPets().add(fionasDog);

        ownerService.save(owner2);

        System.out.println("Load owners.....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);
        vet2.getSpecialities().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("Load vets.....");
    }
}
