package com.untels.estadonutricional.service;

import com.untels.estadonutricional.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PorcentajeSexoAlumnosService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public float obtenerPorcentajeSexoMasculino() {

        long totalAlumnos = alumnoRepository.countAll();

        long totalAlumnosMasculinos = alumnoRepository.countBySexoMasculino();

        if (totalAlumnos == 0) {
            return 0;
        }

        return ((float) totalAlumnosMasculinos / (float) totalAlumnos);
    }

    public float obtenerPorcentajeSexoFemenino() {

        long totalAlumnos = alumnoRepository.countAll();

        long totalAlumnosFemeninos = alumnoRepository.countBySexoFemenino();

        if (totalAlumnos == 0) {
            return 0;
        }

        return ((float) totalAlumnosFemeninos / (float) totalAlumnos);
    }

}
