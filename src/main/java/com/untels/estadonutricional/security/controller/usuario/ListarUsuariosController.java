package com.untels.estadonutricional.security.controller.usuario;

import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.security.dto.param.ListarUsuariosParam;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.service.ListarUsuariosService;
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
@RequestMapping("/api/usuarios")
@CrossOrigin
public class ListarUsuariosController {

    @Autowired
    ListarUsuariosService listarUsuariosService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO')")
    @GetMapping
    public ResponseEntity<?> listarUsuarios(
            ListarUsuariosParam listarUsuariosParam
    ) {
        List<Usuario> alumnos = listarUsuariosService
                .listarUsuarios(listarUsuariosParam);
        return new ResponseEntity<>(
                new Respuesta<>(
                        alumnos,
                        "Listado de usuarios"
                ),
                HttpStatus.OK
        );
    }
}
