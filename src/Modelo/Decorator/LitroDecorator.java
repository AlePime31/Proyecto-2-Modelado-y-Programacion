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
    public abstract double costo();
}

