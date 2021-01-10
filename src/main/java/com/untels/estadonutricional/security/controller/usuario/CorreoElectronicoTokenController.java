package com.untels.estadonutricional.security.controller.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token/")
@CrossOrigin
public class CorreoElectronicoTokenController {

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'MEDICO', 'ALUMNO')")
    @GetMapping("/correo-electronico")
    public ResponseEntity<?> obtenerCorreoElectronicoToken(
            Authentication authentication
    ) {
        String correo = authentication.getName();
        return new ResponseEntity(correo, HttpStatus.OK);
    }
}
