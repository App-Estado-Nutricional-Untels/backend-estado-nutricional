package com.untels.estadonutricional.security.controller.usuario;

import com.untels.estadonutricional.entity.Persona;
import com.untels.estadonutricional.security.dto.request.IniciarSesionBody;
import com.untels.estadonutricional.security.dto.response.UsuarioAutenticadoResponse;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.jwt.JwtProvider;
import com.untels.estadonutricional.security.service.RolService;
import com.untels.estadonutricional.security.service.UsuarioService;
import com.untels.estadonutricional.service.AlumnoService;
import com.untels.estadonutricional.service.MedicoService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Validated
public class IniciarSesionController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    MedicoService medicoService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<?> iniciarSesion(
            @Valid @RequestBody IniciarSesionBody iniciarSesionBody
    ) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                iniciarSesionBody.getCorreoElectronico(),
                                iniciarSesionBody.getClave()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generaToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Usuario usuario = usuarioService
                .obtenerUnoPorCorreoElectronico(userDetails.getUsername())
                .get();

        Persona persona = usuario.getPersona();

        Map<String, Object> datos = new HashMap<>();

        datos.put("persona", persona);

        if (alumnoService.existePorPersona(persona)) {
            datos.put(
                    "codigoUniversitario",
                    alumnoService
                            .obtenerUnoPorPersona(persona)
                            .getCodigoUniversitario());
        }

        if (medicoService.existePorPersona(persona)) {
            datos.put("codigoMedico",
                    medicoService
                            .obtenerUnoPorPersona(persona)
                            .getCodigoMedico());
        }

        return new ResponseEntity(
                new UsuarioAutenticadoResponse(jwt, "Bearer", datos),
                HttpStatus.OK);
    }

}
