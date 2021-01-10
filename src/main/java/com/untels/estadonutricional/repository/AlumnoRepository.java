package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>,
        JpaSpecificationExecutor<Alumno> {

    public boolean existsByCodigoUniversitario(String codigoUniversitario);

    public boolean existsByPersona(Persona persona);

    Optional<Alumno> findByPersona(Persona persona);
}
