package Modelo.Decorator;

import Modelo.Litro;

public abstract class LitroDecorator extends Litro {
    protected Litro litroDecorado;

    public LitroDecorator(Litro litroDecorado) {
        this.litroDecorado = litroDecorado;
    }

    @Override
    public String getDescripcion() {
        return litroDecorado.getDescripcion();
    }
    
    @Override
    public double costo() {
        return litroDecorado.costo(); // Se asegura de calcular el costo acumulativo correctamente
    }
    
}

