package com.untels.estadonutricional.controller.persona;

import com.untels.estadonutricional.dto.param.ListarPersonasParam;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.entity.Persona;
import com.untels.estadonutricional.service.ListarPersonasService;
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
@RequestMapping("/api/personas")
@CrossOrigin
public class ListarPersonasController {

    @Autowired
    ListarPersonasService listarPersonasService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO')")
    @GetMapping
    public ResponseEntity<?> listarPersonas(
            ListarPersonasParam listarPersonasParam
    ) {
        List<Persona> alumnos = listarPersonasService
                .listarPersonas(listarPersonasParam);
        return new ResponseEntity<>(
                new Respuesta<>(
                        alumnos,
                        "Listado de personas"
                ),
                HttpStatus.OK
        );
    }
}
