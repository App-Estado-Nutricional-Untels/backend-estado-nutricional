package com.untels.estadonutricional.controller.alumno;

import com.untels.estadonutricional.dto.param.ListarAlumnosParam;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.service.ListarAlumnosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin
public class ListarAlumnosController {

    @Autowired
    ListarAlumnosService listarAlumnosService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO')")
    @GetMapping
    public ResponseEntity<?> listarAlumnos(
            ListarAlumnosParam listarAlumnosParam
    ) {
        List<Alumno> alumnos = listarAlumnosService
                .listarAlumnos(listarAlumnosParam);
        return new ResponseEntity<>(
                new Respuesta<>(
                        alumnos,
                        "Listado de alumnos"
                ),
                HttpStatus.OK
        );
    }
}
