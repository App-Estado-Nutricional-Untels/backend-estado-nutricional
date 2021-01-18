package com.untels.estadonutricional.controller.categoriaimc;

import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.service.CategoriaIMCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias-imc")
@CrossOrigin
public class CategoriaIMCController {

    @Autowired
    CategoriaIMCService categoriaIMCService;

    @PreAuthorize("hasAnyRole('ALUMNO','ADMINISTRADOR','MEDICO')")
    @GetMapping
    public ResponseEntity<?> listar() {
        return new ResponseEntity<>(
                new Respuesta<>(
                        categoriaIMCService.listarCategoriasIMC(),
                        "Listado de categorías imc"
                ),
                HttpStatus.OK
        );
    }
}
