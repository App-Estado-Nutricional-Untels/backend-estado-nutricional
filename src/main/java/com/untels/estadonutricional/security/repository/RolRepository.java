package com.untels.estadonutricional.security.repository;

import com.untels.estadonutricional.security.entity.Rol;
import com.untels.estadonutricional.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
