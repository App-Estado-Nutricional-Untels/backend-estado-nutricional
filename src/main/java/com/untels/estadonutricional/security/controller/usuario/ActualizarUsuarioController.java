package com.untels.estadonutricional.security.controller.usuario;

import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.entity.Persona;
import com.untels.estadonutricional.security.dto.request.ActualizarUsuarioBody;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.service.UsuarioService;
import com.untels.estadonutricional.service.PersonaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/usuarios")
@CrossOrigin
@Validated
public class ActualizarUsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PersonaService personaService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO', 'ALUMNO')")
    @PutMapping
    public ResponseEntity<?> actualizarUsuarioAutenticado(
            @Valid @RequestBody ActualizarUsuarioBody actualizarUsuarioBody,
            Authentication authentication
    ) {
        String correoElectronico = authentication.getName();

        Usuario usuario = usuarioService
                .obtenerUnoPorCorreoElectronico(correoElectronico)
                .get();

        Persona persona = usuario.getPersona();

        persona.setApepaterno(actualizarUsuarioBody.getApePaterno());
        persona.setApematerno(actualizarUsuarioBody.getApeMaterno());
        persona.setNombre(actualizarUsuarioBody.getNombres());

        personaService.guardar(persona);

        return new ResponseEntity<>(
                new Respuesta<Persona>(
                        persona,
                        "Actualizado"
                ),
                HttpStatus.OK);
    }

}
