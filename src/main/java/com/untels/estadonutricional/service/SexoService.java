package com.untels.estadonutricional.service;

import com.untels.estadonutricional.enums.SexoNombre;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SexoService {

    public List<SexoNombre> listarSexos() {
        return Arrays.asList(SexoNombre.values());
    }

}
