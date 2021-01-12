package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatoAntropometricoRepository extends
        JpaRepository<DatoAntropometrico, Integer> {

    public Optional<DatoAntropometrico> findByAlumno(Alumno alumno);

    public long countByAlumno(Alumno alumno);

    public boolean existsByAlumno(Alumno alumno);

    public boolean existsById(int id);

    public Optional<DatoAntropometrico> findById(int id);
}
