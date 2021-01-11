package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>,
        JpaSpecificationExecutor<Alumno> {

    public boolean existsByCodigoUniversitario(String codigoUniversitario);

    public boolean existsByPersona(Persona persona);

    Optional<Alumno> findByPersona(Persona persona);

    @Query("select count(*) from Alumno a inner join a.persona")
    long countAll();

    @Query("select count(*) from Alumno a inner join a.persona p where p.sexo = 'M'")
    long countBySexoMasculino();

    @Query("select count(*) from Alumno a inner join a.persona p where p.sexo = 'F'")
    long countBySexoFemenino();
}
