package com.untels.estadonutricional.service;

import com.untels.estadonutricional.enums.CategoriaICC;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoriaICCService {

    public List<CategoriaICC> listarCategoriasICC() {
        return Arrays.asList(CategoriaICC.values());
    }
}
