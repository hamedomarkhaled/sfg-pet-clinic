package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());

        // TO-DO check why .builder().id is not working
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Omar");
        owner.setLastName("Hamed");

        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(1L);

        assertEquals(1L, owner.getId());
    }

    @Test
    void saveExistingId() {
        Owner owner2 = new Owner();
        owner2.setId(2L);

        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(2L, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner3 = new Owner();
        Owner savedOwner = ownerMapService.save(owner3);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(1L));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(1L);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner omar = ownerMapService.findByLastName("Hamed");

        assertNotNull(omar);
        assertEquals(1L, omar.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner omar = ownerMapService.findByLastName("sdasd");
        assertNull(omar);
    }
}