package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest {
    public static final String LAST_NAME = "Smith";
    @Mock
     OwnerRepository ownerRepository;
     @Mock
     PetRepository petRepository;
     @Mock
     PetTypeRepository petTypeRepository;

     @InjectMocks
     OwnerSDJpaService ownerSDJpaService;

     Owner owner3;

    @BeforeEach
    void setUp(){
        owner3 =  new Owner();
        owner3.setId(4L);
        owner3.setLastName(LAST_NAME);
    }

    @Test
    void findByLastName(){
        Owner returnedOwner = new Owner();
        returnedOwner.setId(1L);
        returnedOwner.setLastName(LAST_NAME);

        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);

        Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }
    @Test
    void findAll(){
        Set<Owner> returnedOwnerSet = new HashSet<>();
        Owner owner1 = new Owner();
        owner1.setId(2L);
        Owner owner2 = new Owner();
        owner2.setId(3L);
        returnedOwnerSet.add(owner1);
        returnedOwnerSet.add(owner2);

        when(ownerRepository.findAll()).thenReturn(returnedOwnerSet);

        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());

    }
    @Test
    void findById(){
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner3));

        Owner owner = ownerSDJpaService.findById(4L);

        assertEquals(4L, owner.getId());
        assertNotNull(owner);


    }
    @Test
    void findByIdNotFound(){
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = ownerSDJpaService.findById(1L);

        assertNull(owner);
    }
    @Test
    void save(){
        Owner ownerToSave = new Owner();
        ownerToSave.setId(1L);
        ownerToSave.setLastName("Hamed");

        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        Owner savedOwner = ownerSDJpaService.save(ownerToSave);
        assertNotNull(savedOwner);


    }
    @Test
    void delete(){
        ownerSDJpaService.delete(owner3);

        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());

    }
    @Test
    void deleteById(){
        ownerSDJpaService.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());

    }
}
