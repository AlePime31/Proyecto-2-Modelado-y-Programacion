package Modelo.Decorator;

import Modelo.Litro;

public class ToppingFrutosRojos extends LitroDecorator {
    public ToppingFrutosRojos(Litro litro) {
        super(litro);
    }

    @Override
    public String getDescripcion() {
        return litroDecorado.getDescripcion() + ", con Frutos Rojos";
    }

    @Override
    public double costo() {
        return litroDecorado.costo() + 1.00; // Costo del topping
    }
}