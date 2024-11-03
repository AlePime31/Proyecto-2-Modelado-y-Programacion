package Modelo.Decorator;

import Modelo.Litro;

public class ToppingHieloExtra extends LitroDecorator {
    public ToppingHieloExtra(Litro litro) {
        super(litro);
    }

    @Override
    public String getDescripcion() {
        return litroDecorado.getDescripcion() + ", con Hielo extra";
    }

    @Override
    public double costo() {
        return litroDecorado.costo() + 1.00; // Costo del topping
    }
    
}
