package com.untels.estadonutricional.service;

import com.untels.estadonutricional.repository.CategoriaIMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaIMCService {

    @Autowired
    private CategoriaIMCRepository categoriaIMCRepository;
}
