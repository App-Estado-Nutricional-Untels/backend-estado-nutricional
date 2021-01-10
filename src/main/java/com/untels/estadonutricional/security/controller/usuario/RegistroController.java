
package com.untels.estadonutricional.security.controller.usuario;

import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.enums.SexoNombre;
import com.untels.estadonutricional.security.dto.request.RegistroBody;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.enums.RolNombre;
import com.untels.estadonutricional.security.service.NuevoUsuarioService;
import com.untels.estadonutricional.security.service.UsuarioService;
import com.untels.estadonutricional.service.AlumnoService;
import com.untels.estadonutricional.service.CodigoMedValidoService;
import com.untels.estadonutricional.service.CodigoUniValidoService;
import com.untels.estadonutricional.service.MedicoService;
import com.untels.estadonutricional.service.PersonaService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class RegistroController {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private CodigoMedValidoService codigoMedValidoService;

    @Autowired
    private CodigoUniValidoService codigoUniValidoService;

    @Autowired
    NuevoUsuarioService nuevoUsuarioService;

    @PostMapping("/registro")
    public ResponseEntity<?> nuevoUsuario(
            @Valid @RequestBody RegistroBody registroBody
    ) {
        // Validación de errores de duplicación
        List<Error> errores = getErrores(registroBody);

        if (!errores.isEmpty()) {
            return new ResponseEntity<>(
                    new RespuestaError(errores),
                    HttpStatus.BAD_REQUEST);
        }

        RolNombre rolNombre = getRolNombre(registroBody.getRol());

        // Guardado de usuario, persona y opcionalmente médico o alumno
        Usuario usuario = nuevoUsuarioService.crearUsuarioCompleto(
                new NuevoUsuarioService.UsuarioCompleto(
                        registroBody.getNombres(),
                        registroBody.getApePaterno(),
                        registroBody.getApeMaterno(),
                        registroBody.getDni(),
                        registroBody.getCorreoElectronico(),
                        registroBody.getCodigo(),
                        null,
                        (registroBody.getSexo().equalsIgnoreCase("F")
                        ? SexoNombre.F
                        : SexoNombre.M),
                        rolNombre,
                        registroBody.getClave()));

        return new ResponseEntity(new Respuesta(
                usuario,
                "Usuario completo creado"),
                HttpStatus.OK);
    }

    private List<Error> getErrores(RegistroBody registroBody) {
        List<Error> errores = new ArrayList<>();

        if (usuarioService.existePorCorreoElectronico(
                registroBody.getCorreoElectronico()
        )) {
            errores.add(new Error(
                    "correoElectronico",
                    "el correo electrónico ya existe"));
        }

        if (personaService.existePorDni(
                registroBody.getDni()
        )) {
            errores.add(new Error(
                    "dni",
                    "el dni ya existe"));
        }

        if (registroBody.getRol().equalsIgnoreCase("ALUMNO")
                || registroBody.getRol().equalsIgnoreCase("MEDICO")) {
            if (registroBody.getCodigo() == null) {
                errores.add(new Error(
                        "codigo",
                        "el codigo no puede ser nulo"));
            } else {
                if (registroBody.getRol().equalsIgnoreCase("ALUMNO")) {
                    if (alumnoService.existePorCodigoUniversitario(
                            registroBody.getCodigo()
                    )) {
                        errores.add(new Error(
                                "codigo",
                                "el codigo ya existe"));
                    }

                    if (!codigoUniValidoService.esValido(
                            registroBody.getCodigo()
                    )) {
                        errores.add(new Error(
                                "codigo",
                                "el codigo no está permitido"));
                    }
                } else if (registroBody.getRol().equalsIgnoreCase("MEDICO")) {
                    if (medicoService.existePorCodigoMedico(
                            registroBody.getCodigo()
                    )) {
                        errores.add(new Error(
                                "codigo",
                                "el codigo ya existe"));
                    }

                    if (!codigoMedValidoService.esValido(
                            registroBody.getCodigo()
                    )) {
                        errores.add(new Error(
                                "codigo",
                                "el codigo no está permitido"));
                    }
                }
            }
        }

        return errores;
    }

    private RolNombre getRolNombre(String nombre) {
        if (nombre.equalsIgnoreCase("alumno")) {
            return RolNombre.ROLE_ALUMNO;
        }

        return RolNombre.ROLE_MEDICO;

    }

}
