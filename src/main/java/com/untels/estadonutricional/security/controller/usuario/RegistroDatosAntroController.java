package com.untels.estadonutricional.security.controller.usuario;

import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import com.untels.estadonutricional.entity.Persona;
import com.untels.estadonutricional.enums.NivelEstres;
import com.untels.estadonutricional.security.dto.request.RegistroDatosAntroBody;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.service.DatosAntropometricosService;
import com.untels.estadonutricional.security.service.UsuarioService;
import com.untels.estadonutricional.service.AlumnoService;
import com.untels.estadonutricional.service.PersonaService;
import com.untels.estadonutricional.utils.GregorianCalendarParser;
import java.util.GregorianCalendar;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/alumnos/datos-antropometricos")
@CrossOrigin
@Validated
public class RegistroDatosAntroController {

    private String correo;

    @Autowired
    private DatosAntropometricosService datoAntropometricoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private PersonaService personaService;

    @PreAuthorize("hasRole('ALUMNO')")
    @PostMapping("/inicial")
    public ResponseEntity<?> registroDatosAntropometricos(
            @Valid @RequestBody RegistroDatosAntroBody registroDatosAntroBody,
            Authentication authentication
    ) {

        //Validar que no existan datos ingresados
        this.correo = authentication.getName();
        Usuario usuario = usuarioService.obtenerUnoPorCorreoElectronico(this.correo).get();
        Alumno alumno = alumnoService.obtenerUnoPorPersona(usuario.getPersona());

        if (usuario == null || alumno == null || datoAntropometricoService.existeRegistrosPorAlumno(alumno)) {
            return new ResponseEntity(
                    new RespuestaError(new Error("alumno", "existen registros iniciales")),
                    HttpStatus.BAD_REQUEST);
        }

        Persona persona = usuario.getPersona();
        persona.setFechaNacimiento(GregorianCalendarParser.parse(registroDatosAntroBody.getFechaNacimiento()));
        personaService.guardar(persona);

        DatoAntropometrico datoAntropometrico = datoAntropometricoService.registrarDatosAntropometricos(
                new DatoAntropometrico(
                        registroDatosAntroBody.getEstatura(),
                        registroDatosAntroBody.getPeso(),
                        registroDatosAntroBody.getContornoCintura(),
                        registroDatosAntroBody.getContornoCadera(),
                        NivelEstres.fromString(registroDatosAntroBody.getNivelEstres()),
                        registroDatosAntroBody.getActividadFisica(),
                        registroDatosAntroBody.getRendimientoAcademico(),
                        new GregorianCalendar(),
                        alumno));

        return new ResponseEntity(new Respuesta(
                datoAntropometrico,
                "Datos antropometricos registrado"),
                HttpStatus.OK);
    }

}
