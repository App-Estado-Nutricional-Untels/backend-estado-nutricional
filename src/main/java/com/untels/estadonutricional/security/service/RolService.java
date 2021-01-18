package com.untels.estadonutricional.security.service;

import com.untels.estadonutricional.security.entity.Rol;
import com.untels.estadonutricional.security.enums.RolNombre;
import com.untels.estadonutricional.security.repository.RolRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    public List<String> listarRolNombres() {
        return Stream.of(RolNombre.values())
                .map(rolNombre -> rolNombre.name().substring(5))
                .collect(Collectors.toList());
    }
}
