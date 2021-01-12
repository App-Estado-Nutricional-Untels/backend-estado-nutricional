
package com.untels.estadonutricional.utils.icc;

public class ICCCalculator implements ICCCalculo{

    private float contornoCintura;
    private float contornoCadera;

    public ICCCalculator(float contornoCintura, float contornoCadera) throws ICCCalculoException {
        
        if( contornoCintura == 0 || contornoCadera == 0 ){
            throw new ICCCalculoException("El contorno de tu cintura ni contorno de tu cadera pueden ser cero");
        }
        
        this.contornoCintura = contornoCintura;
        this.contornoCadera = contornoCadera;
    }
    
    @Override
    public float obtenerICC() {
        return contornoCintura/contornoCadera;
    }
    
}
