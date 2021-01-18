package com.untels.estadonutricional.security.controller.rol;

import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RolController {

    @Autowired
    RolService rolService;

    @PreAuthorize("hasAnyRole('ALUMNO','ADMINISTRADOR','MEDICO')")
    @GetMapping
    public ResponseEntity<?> listar() {
        return new ResponseEntity<>(
                new Respuesta<>(
                        rolService.listarRolNombres(),
                        "Listado de roles"
                ),
                HttpStatus.OK
        );
    }
}
