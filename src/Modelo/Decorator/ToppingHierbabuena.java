package Modelo.Decorator;

import Modelo.Litro;

public class ToppingHierbabuena extends LitroDecorator {
    public ToppingHierbabuena(Litro litro) {
        super(litro);
    }

    @Override
    public String getDescripcion() {
        return litroDecorado.getDescripcion() + ", con Hierbabuena";
    }

    @Override
    public double costo() {
        return litroDecorado.costo() + 1.00; // Costo del topping
    }
}
