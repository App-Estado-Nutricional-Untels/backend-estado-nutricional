package com.untels.estadonutricional.security.service;

import com.untels.estadonutricional.security.dto.param.ListarUsuariosParam;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.repository.UsuarioRepository;
import com.untels.estadonutricional.security.specification.usuario.ListarUsuariosSpecification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ListarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarAlumnos(ListarUsuariosParam listarUsuariosParam) {
        return usuarioRepository.findAll(
                createSpecification(listarUsuariosParam));
    }

    private Specification<Usuario> createSpecification(
            ListarUsuariosParam param
    ) {
        Specification<Usuario> specification = Specification.where(null);

        if (param != null) {
            if (param.getNombres() != null) {
                specification
                        = specification.and(
                                ListarUsuariosSpecification
                                        .nombresContains(
                                                param.getNombres()));
            }

            if (param.getApePaterno() != null) {
                specification
                        = specification.and(
                                ListarUsuariosSpecification
                                        .apePaternoContains(
                                                param.getApePaterno()));
            }

            if (param.getApeMaterno() != null) {
                specification
                        = specification.and(
                                ListarUsuariosSpecification
                                        .apeMaternoContains(
                                                param.getApeMaterno()));
            }

            if (param.getDni() != null) {
                specification
                        = specification.and(
                                ListarUsuariosSpecification
                                        .dniContains(param.getDni()));
            }

            if (param.getCorreoElectronico() != null) {
                specification
                        = specification.and(
                                ListarUsuariosSpecification
                                        .correoElectronicoContains(
                                                param.getCorreoElectronico()));
            }

            if (param.getEdadMin() != null) {
                specification
                        = specification.and(
                                ListarUsuariosSpecification
                                        .edadMin(param.getEdadMin()));
            }

            if (param.getEdadMax() != null) {
                specification
                        = specification.and(
                                ListarUsuariosSpecification
                                        .edadMax(param.getEdadMax()));
            }
        }

        return specification;
    }
}
