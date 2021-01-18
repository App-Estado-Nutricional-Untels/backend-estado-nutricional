package com.untels.estadonutricional.service;

import com.untels.estadonutricional.enums.CategoriaIMC;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoriaIMCService {

    public List<CategoriaIMC> listarCategoriasIMC() {
        return Arrays.asList(CategoriaIMC.values());
    }
}
