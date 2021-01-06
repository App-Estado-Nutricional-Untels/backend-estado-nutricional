package com.untels.estadonutricional.service;

import com.untels.estadonutricional.repository.CodigoMedValidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoMedValidoService {

    @Autowired
    private CodigoMedValidoRepository codigoMedValidoRepository;

    public boolean esValido(String codigo) {
        return codigoMedValidoRepository.existsByCodigoMedico(codigo);
    }
}
