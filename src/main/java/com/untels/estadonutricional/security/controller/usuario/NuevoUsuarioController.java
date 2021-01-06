package com.untels.estadonutricional.security.controller.usuario;

import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.enums.SexoNombre;
import com.untels.estadonutricional.security.dto.request.NuevoUsuarioBody;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.enums.RolNombre;
import com.untels.estadonutricional.security.service.NuevoUsuarioService;
import com.untels.estadonutricional.security.service.NuevoUsuarioService.UsuarioCompleto;
import com.untels.estadonutricional.security.service.UsuarioService;
import com.untels.estadonutricional.service.AlumnoService;
import com.untels.estadonutricional.service.CodigoMedValidoService;
import com.untels.estadonutricional.service.CodigoUniValidoService;
import com.untels.estadonutricional.service.MedicoService;
import com.untels.estadonutricional.service.PersonaService;
import com.untels.estadonutricional.utils.GregorianCalendarParser;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
@Validated
public class NuevoUsuarioController {

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
    PasswordEncoder passwordEncoder;

    @Autowired
    NuevoUsuarioService nuevoUsuarioService;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<?> nuevoUsuario(
            @Valid @RequestBody NuevoUsuarioBody nuevoUsuarioBody
    ) {
        // Validación de errores de duplicación
        List<Error> errores = getErrores(nuevoUsuarioBody);

        if (!errores.isEmpty()) {
            return new ResponseEntity<>(
                    new RespuestaError(errores),
                    HttpStatus.BAD_REQUEST);
        }

        RolNombre rolNombre = getRolNombre(nuevoUsuarioBody.getRol());

        // Guardado de usuario, persona y opcionalmente médico o alumno
        Usuario usuario = nuevoUsuarioService.crearUsuarioCompleto(
                new UsuarioCompleto(
                        nuevoUsuarioBody.getNombres(),
                        nuevoUsuarioBody.getApePaterno(),
                        nuevoUsuarioBody.getApeMaterno(),
                        nuevoUsuarioBody.getDni(),
                        nuevoUsuarioBody.getCorreoElectronico(),
                        nuevoUsuarioBody.getCodigo(),
                        GregorianCalendarParser.parse(
                                nuevoUsuarioBody.getFechaNacimiento()),
                        (nuevoUsuarioBody.getSexo().equalsIgnoreCase("F")
                        ? SexoNombre.F
                        : SexoNombre.M),
                        rolNombre,
                        nuevoUsuarioBody.getClave()));

        return new ResponseEntity(new Respuesta(
                usuario,
                "Usuario completo creado"),
                HttpStatus.OK);
    }

    private List<Error> getErrores(NuevoUsuarioBody nuevoUsuarioBody) {
        List<Error> errores = new ArrayList<>();

        if (usuarioService.existePorCorreoElectronico(
                nuevoUsuarioBody.getCorreoElectronico()
        )) {
            errores.add(new Error(
                    "correoElectronico",
                    "el correo electrónico ya existe"));
        }

        if (personaService.existePorDni(
                nuevoUsuarioBody.getDni()
        )) {
            errores.add(new Error(
                    "dni",
                    "el dni ya existe"));
        }

        if (nuevoUsuarioBody.getRol().equalsIgnoreCase("ALUMNO")
                || nuevoUsuarioBody.getRol().equalsIgnoreCase("MEDICO")) {
            if (nuevoUsuarioBody.getCodigo() == null) {
                errores.add(new Error(
                        "codigo",
                        "el codigo no puede ser nulo"));
            } else {
                if (nuevoUsuarioBody.getRol().equalsIgnoreCase("ALUMNO")) {
                    if (alumnoService.existePorCodigoUniversitario(
                            nuevoUsuarioBody.getCodigo()
                    )) {
                        errores.add(new Error(
                                "codigo",
                                "el codigo ya existe"));
                    }

                    if (!codigoUniValidoService.esValido(
                            nuevoUsuarioBody.getCodigo()
                    )) {
                        errores.add(new Error(
                                "codigo",
                                "el codigo no está permitido"));
                    }
                } else if (nuevoUsuarioBody.getRol().equalsIgnoreCase("MEDICO")) {
                    if (medicoService.existePorCodigoMedico(
                            nuevoUsuarioBody.getCodigo()
                    )) {
                        errores.add(new Error(
                                "codigo",
                                "el codigo ya existe"));
                    }

                    if (!codigoMedValidoService.esValido(
                            nuevoUsuarioBody.getCodigo()
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

        if (nombre.equalsIgnoreCase("medico")) {
            return RolNombre.ROLE_MEDICO;
        }

        return RolNombre.ROLE_ADMINISTRADOR;
    }

}
