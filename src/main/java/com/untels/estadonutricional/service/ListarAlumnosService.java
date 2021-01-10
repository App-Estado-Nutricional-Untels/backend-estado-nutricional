package com.untels.estadonutricional.service;

import com.untels.estadonutricional.dto.param.ListarAlumnosParam;
import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.repository.AlumnoRepository;
import com.untels.estadonutricional.specification.alumno.ListarAlumnosSpecification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ListarAlumnosService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> listarAlumnos(ListarAlumnosParam listarAlumnosParam) {
        return alumnoRepository.findAll(
                createSpecification(listarAlumnosParam));
    }

    private Specification<Alumno> createSpecification(
            ListarAlumnosParam param
    ) {
        Specification<Alumno> specification = Specification.where(null);

        if (param != null) {
            if (param.getNombres() != null) {
                specification
                        = specification.and(
                                ListarAlumnosSpecification
                                        .nombresContains(
                                                param.getNombres()));
            }

            if (param.getApePaterno() != null) {
                specification
                        = specification.and(
                                ListarAlumnosSpecification
                                        .apePaternoContains(
                                                param.getApePaterno()));
            }

            if (param.getApeMaterno() != null) {
                specification
                        = specification.and(
                                ListarAlumnosSpecification
                                        .apeMaternoContains(
                                                param.getApeMaterno()));
            }

            if (param.getDni() != null) {
                specification
                        = specification.and(
                                ListarAlumnosSpecification
                                        .dniContains(param.getDni()));
            }

            if (param.getCodigoUniversitario() != null) {
                specification
                        = specification.and(
                                ListarAlumnosSpecification
                                        .codigoUniversitarioContains(
                                                param.getCodigoUniversitario()));
            }

            if (param.getEdadMin() != null) {
                specification
                        = specification.and(
                                ListarAlumnosSpecification
                                        .edadMin(param.getEdadMin()));
            }

            if (param.getEdadMax() != null) {
                specification
                        = specification.and(
                                ListarAlumnosSpecification
                                        .edadMax(param.getEdadMax()));
            }
        }

        return specification;
    }
}
