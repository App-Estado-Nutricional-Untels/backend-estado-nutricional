package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    public boolean existsByDni(String dni);

    public Persona findById(int id);
}
