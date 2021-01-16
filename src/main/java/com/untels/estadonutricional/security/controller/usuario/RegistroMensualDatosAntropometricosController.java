package com.untels.estadonutricional.security.controller.usuario;

import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import com.untels.estadonutricional.enums.NivelEstres;
import com.untels.estadonutricional.security.dto.request.RegistroMensualDatosAntropometricosBody;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.service.DatosAntropometricosService;
import com.untels.estadonutricional.security.service.UsuarioService;
import com.untels.estadonutricional.service.AlumnoService;
import java.util.Calendar;
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
public class RegistroMensualDatosAntropometricosController {

    @Autowired
    private DatosAntropometricosService datoAntropometricoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlumnoService alumnoService;

    @PreAuthorize("hasRole('ALUMNO')")
    @PostMapping
    public ResponseEntity<?> registroMensualDatosAntropometricos(
            @Valid @RequestBody RegistroMensualDatosAntropometricosBody registroMensualDatosAntroBody,
            Authentication authentication
    ) {

        int dia = new GregorianCalendar().get(Calendar.DATE);

        if (!(dia >= 1 && dia <= 12)) {
            return new ResponseEntity(
                    new RespuestaError(new Error("fecha", "El registro esta disponible en la primera semana de cada mes")),
                    HttpStatus.BAD_REQUEST);
        }

        String correo = authentication.getName();
        Usuario usuario = usuarioService.obtenerUnoPorCorreoElectronico(correo).get();
        Alumno alumno = alumnoService.obtenerUnoPorPersona(usuario.getPersona());

        if (usuario == null || alumno == null || !datoAntropometricoService.existeRegistrosPorAlumno(alumno)) {
            return new ResponseEntity(
                    new RespuestaError(new Error("alumno", "no existen registros iniciales")),
                    HttpStatus.BAD_REQUEST);
        }

        int mes = new GregorianCalendar().get(Calendar.MONTH);
        DatoAntropometrico ultimoDatoAntropometrico = datoAntropometricoService.obtenerUltimoPorAlumnoId(alumno.getId()).get();

        if (mes == ultimoDatoAntropometrico.getFechaRegistro().get(Calendar.MONTH)) {
            return new ResponseEntity(
                    new RespuestaError(new Error("registro", "ya existen datos antropometricos registrados en este mes")),
                    HttpStatus.BAD_REQUEST);
        }

        DatoAntropometrico datoAntropometrico = datoAntropometricoService.registrarDatosAntropometricos(
                new DatoAntropometrico(
                        registroMensualDatosAntroBody.getEstatura(),
                        registroMensualDatosAntroBody.getPeso(),
                        registroMensualDatosAntroBody.getContornoCintura(),
                        registroMensualDatosAntroBody.getContornoCadera(),
                        NivelEstres.fromString(registroMensualDatosAntroBody.getNivelEstres()),
                        registroMensualDatosAntroBody.getActividadFisica(),
                        registroMensualDatosAntroBody.getRendimientoAcademico(),
                        new GregorianCalendar(),
                        alumno));

        return new ResponseEntity(new Respuesta(
                datoAntropometrico,
                "Datos antropometricos registrado"),
                HttpStatus.OK);

    }
}
