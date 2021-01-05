package com.untels.estadonutricional.service;

import com.untels.estadonutricional.repository.CategoriaICCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaICCService {

    @Autowired
    private CategoriaICCRepository categoriaICCRepository;
}
