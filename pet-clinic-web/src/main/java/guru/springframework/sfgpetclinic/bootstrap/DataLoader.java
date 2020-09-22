package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final SpecialityService specialityService;
    private final VisitService visitService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dogPetType = new PetType();
        dogPetType.setName("Dog");

        PetType savedDogPetType = petTypeService.save(dogPetType);

        PetType catPetType = new PetType();
        catPetType.setName("Cat");

        PetType savedCatPetType = petTypeService.save(catPetType);

        System.out.println("Loaded PetType counts: "+ petTypeService.findAll().size());


        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Waston");
        owner1.setAddress("70 Rheiensberger str");
        owner1.setCity("Berlin");
        owner1.setTelephone("+201010816831");

        Pet owner1Pet = new Pet();
        owner1Pet.setPetType(savedDogPetType);
        owner1Pet.setOwner(owner1);
        owner1Pet.setBirthdate(LocalDate.now());
        owner1Pet.setName("Rosco");
        owner1.getPets().add(owner1Pet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("20 Andrea str");
        owner2.setCity("Berlin");
        owner2.setTelephone("+4915124357337");

        Pet owner2Pet = new Pet();
        owner2Pet.setPetType(savedCatPetType);
        owner2Pet.setOwner(owner2);
        owner2Pet.setBirthdate(LocalDate.now());
        owner2Pet.setName("Caaat Shirazy");
        owner2.getPets().add(owner2Pet);


        ownerService.save(owner2);

        Visit owner2PetVisit = new Visit();
        owner2PetVisit.setDate(LocalDate.now());
        owner2PetVisit.setPet(owner2Pet);
        owner2PetVisit.setDescription("Sneezy Kitty");

        visitService.save(owner2PetVisit);

        Owner owner3 = new Owner();
        owner3.setFirstName("Omar");
        owner3.setLastName("Hamed");


        ownerService.save(owner3);

        System.out.println("Loaded Owners...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality radiologySavedSpeciality = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality surgeryySavedSpeciality = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality dentistrySavedSpeciality = specialityService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(radiologySavedSpeciality);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(surgeryySavedSpeciality);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");


        System.out.println("number of saved pets " + petService.findAll().size());
    }
}
