package com.untels.estadonutricional.service;

import com.untels.estadonutricional.dto.param.ListarPersonasParam;
import com.untels.estadonutricional.entity.Persona;
import com.untels.estadonutricional.repository.PersonaRepository;
import com.untels.estadonutricional.specification.persona.ListarPersonasSpecification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ListarPersonasService {

    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> listarPersonas(ListarPersonasParam listarPersonasParam) {
        return personaRepository.findAll(
                createSpecification(listarPersonasParam));
    }

    private Specification<Persona> createSpecification(
            ListarPersonasParam param
    ) {
        Specification<Persona> specification = Specification.where(null);

        if (param != null) {
            if (param.getNombres() != null) {
                specification
                        = specification.and(
                                ListarPersonasSpecification
                                        .nombresContains(
                                                param.getNombres()));
            }

            if (param.getApePaterno() != null) {
                specification
                        = specification.and(
                                ListarPersonasSpecification
                                        .apePaternoContains(
                                                param.getApePaterno()));
            }

            if (param.getApeMaterno() != null) {
                specification
                        = specification.and(
                                ListarPersonasSpecification
                                        .apeMaternoContains(
                                                param.getApeMaterno()));
            }

            if (param.getDni() != null) {
                specification
                        = specification.and(
                                ListarPersonasSpecification
                                        .dniContains(param.getDni()));
            }

            if (param.getCorreoElectronico() != null) {
                specification
                        = specification.and(
                                ListarPersonasSpecification
                                        .correoElectronicoContains(
                                                param.getCorreoElectronico()));
            }

            if (param.getEdadMin() != null) {
                specification
                        = specification.and(
                                ListarPersonasSpecification
                                        .edadMin(param.getEdadMin()));
            }

            if (param.getEdadMax() != null) {
                specification
                        = specification.and(
                                ListarPersonasSpecification
                                        .edadMax(param.getEdadMax()));
            }
        }

        return specification;
    }
}
