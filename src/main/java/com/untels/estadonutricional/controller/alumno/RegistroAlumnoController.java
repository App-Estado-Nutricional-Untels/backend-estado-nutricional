
package com.untels.estadonutricional.controller.alumno;

import com.untels.estadonutricional.dto.request.RegistroAlumnoBody;
import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.enums.SexoNombre;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.enums.RolNombre;
import com.untels.estadonutricional.security.service.NuevoUsuarioService;
import com.untels.estadonutricional.security.service.UsuarioService;
import com.untels.estadonutricional.service.AlumnoService;
import com.untels.estadonutricional.service.CodigoUniValidoService;
import com.untels.estadonutricional.service.PersonaService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class RegistroAlumnoController {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private CodigoUniValidoService codigoUniValidoService;

    @Autowired
    NuevoUsuarioService nuevoUsuarioService;
    
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO')")
    @PostMapping("/alumnos")
    public ResponseEntity<?> nuevoUsuario(
            @Valid @RequestBody RegistroAlumnoBody registroAlumnoBody
    ) {
        // Validación de errores de duplicación
        List<Error> errores = getErrores(registroAlumnoBody);

        if (!errores.isEmpty()) {
            return new ResponseEntity<>(
                    new RespuestaError(errores),
                    HttpStatus.BAD_REQUEST);
        }

        // Guardado de usuario, persona y opcionalmente médico o alumno
        Usuario usuario = nuevoUsuarioService.crearUsuarioCompleto(
                new NuevoUsuarioService.UsuarioCompleto(
                        registroAlumnoBody.getNombres(),
                        registroAlumnoBody.getApePaterno(),
                        registroAlumnoBody.getApeMaterno(),
                        registroAlumnoBody.getDni(),
                        registroAlumnoBody.getCorreoElectronico(),
                        registroAlumnoBody.getCodigo(),
                        null,
                        (registroAlumnoBody.getSexo().equalsIgnoreCase("F")
                        ? SexoNombre.F
                        : SexoNombre.M),
                        RolNombre.ROLE_ALUMNO,
                        registroAlumnoBody.getClave()));

        return new ResponseEntity(new Respuesta(
                usuario,
                "Usuario completo creado"),
                HttpStatus.OK);
    }

    private List<Error> getErrores(RegistroAlumnoBody registroAlumnoBody) {
        List<Error> errores = new ArrayList<>();

        if (usuarioService.existePorCorreoElectronico(
                registroAlumnoBody.getCorreoElectronico()
        )) {
            errores.add(new Error(
                    "correoElectronico",
                    "el correo electrónico ya existe"));
        }

        if (personaService.existePorDni(
                registroAlumnoBody.getDni()
        )) {
            errores.add(new Error(
                    "dni",
                    "el dni ya existe"));
        }

        if (registroAlumnoBody.getCodigo() == null) {
                errores.add(new Error(
                        "codigo",
                        "el codigo no puede ser nulo"));
            } else {
            
                if (alumnoService.existePorCodigoUniversitario(
                            registroAlumnoBody.getCodigo()
                    )) {
                        errores.add(new Error(
                                "codigo",
                                "el codigo ya existe"));
                    }

                    if (!codigoUniValidoService.esValido(
                            registroAlumnoBody.getCodigo()
                    )) {
                        errores.add(new Error(
                                "codigo",
                                "el codigo no está permitido"));
                    }
            }

        return errores;
    }

}
