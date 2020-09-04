package guru.springframework.sfgpetclinic.model.services;

import guru.springframework.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);

    Vet save(Vet Vet);

    Set<Vet> findAll();
}