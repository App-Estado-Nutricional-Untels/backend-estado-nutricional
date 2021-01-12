
package com.untels.estadonutricional.utils.imc;

public class IMCCalculator implements IMCCalculo{
    
    private float altura;
    private float peso;

    public IMCCalculator(float altura, float peso) throws IMCCalculoException {
        
        if( altura == 0 || peso == 0 ){
            throw new IMCCalculoException("La altura ni peso pueden ser cero");
        }
        
        this.altura = altura;
        this.peso = peso;
    }
    
    @Override
    public float obtenerIMC(){
        return peso/(float) Math.pow(altura,2);
    }
    
}
