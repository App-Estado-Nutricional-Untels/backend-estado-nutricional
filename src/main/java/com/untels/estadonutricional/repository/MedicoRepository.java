package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.Medico;
import com.untels.estadonutricional.entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    public boolean existsByCodigoMedico(String codigoMedico);

    public boolean existsByPersona(Persona persona);

    public Optional<Medico> findByPersona(Persona persona);
}
