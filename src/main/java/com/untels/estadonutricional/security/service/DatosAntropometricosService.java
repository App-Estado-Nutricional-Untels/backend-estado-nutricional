
package com.untels.estadonutricional.security.service;

import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import com.untels.estadonutricional.enums.CategoriaICC;
import com.untels.estadonutricional.enums.CategoriaIMC;
import com.untels.estadonutricional.enums.SexoNombre;
import com.untels.estadonutricional.repository.DatoAntropometricoRepository;
import com.untels.estadonutricional.utils.icc.ICCCalculator;
import com.untels.estadonutricional.utils.icc.ICCCalculoException;
import com.untels.estadonutricional.utils.imc.IMCCalculator;
import com.untels.estadonutricional.utils.imc.IMCCalculoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosAntropometricosService {
    
    @Autowired
    private DatoAntropometricoRepository datoAntropometricoRepository;
    
    public DatoAntropometrico registrarDatosAntropometricos(DatoAntropometrico datoAntropometrico){
        
        try {
            // Calcular IMC e ICC segun datos(esperando formula..)
            float imc = new IMCCalculator(datoAntropometrico.getEstatura(),datoAntropometrico.getPeso()).obtenerIMC();
            float icc = new ICCCalculator(datoAntropometrico.getContornoCintura(),datoAntropometrico.getContornoCadera()).obtenerICC();
        
            datoAntropometrico.setValorIMC(imc);
            datoAntropometrico.setValorICC(icc);
            datoAntropometrico.setCategoriaimc(clasificarIMC(imc));
            datoAntropometrico.setCategoriaicc(clasificarICC(icc,datoAntropometrico.getAlumno().getPersona().getSexo()));
            
        } catch (IMCCalculoException | ICCCalculoException ex) {
            Logger.getLogger(DatosAntropometricosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        datoAntropometricoRepository.save(datoAntropometrico);
        return datoAntropometrico;
    }
    
    public DatoAntropometrico obtenerAntropometricoPorAlumno(Alumno alumno){
        return datoAntropometricoRepository.findByAlumno(alumno).get();
    }
    
    public boolean existeRegistrosPorAlumno(Alumno alumno){
        long registros = datoAntropometricoRepository.countByAlumno(alumno);
        return registros>0;
    }
    
    private CategoriaIMC clasificarIMC(float imc){
        if(imc<16){
            return CategoriaIMC.DELGADEZ_GRADO_III;
        }else if(imc<17){
            return CategoriaIMC.DELGADEZ_GRADO_II;
        }else if(imc<18.5){
            return CategoriaIMC.DELGADEZ_GRADO_I;
        }else if(imc<25){
            return CategoriaIMC.NORMAL;
        }else if(imc<30){
            return CategoriaIMC.SOBREPESO;
        }else if(imc<35){
            return CategoriaIMC.OBESIDAD_GRADO_I;
        }else if(imc<40){
            return CategoriaIMC.OBESIDAD_GRADO_II;
        }
        
        return CategoriaIMC.OBESIDAD_GRADO_III;
    }
    
    private CategoriaICC clasificarICC(float icc, SexoNombre sexo){
        if(sexo == SexoNombre.M){
            if(icc<0.95) return CategoriaICC.RIESGO_BAJO;
            else if(icc<=1) return CategoriaICC.RIESGO_MODERADO;
            return CategoriaICC.RIESGO_ALTO;
        }
        
        if(icc<0.80) return CategoriaICC.RIESGO_BAJO;
        else if(icc<=0.85) return CategoriaICC.RIESGO_MODERADO;
        return CategoriaICC.RIESGO_ALTO;
    }
}
