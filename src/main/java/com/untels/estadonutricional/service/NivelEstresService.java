package com.untels.estadonutricional.service;

import com.untels.estadonutricional.enums.NivelEstres;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NivelEstresService {

    public List<NivelEstres> listarNivelesEstres() {
        return Arrays.asList(NivelEstres.values());
    }
}
