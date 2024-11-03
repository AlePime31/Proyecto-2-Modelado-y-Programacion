package Modelo.Decorator;

import Modelo.Litro;

public class ToppingRodajasLimon extends LitroDecorator {
    public ToppingRodajasLimon(Litro litro) {
        super(litro);
    }

    @Override
    public String getDescripcion() {
        return litroDecorado.getDescripcion() + ", con Rodajas de Limon";
    }

    @Override
    public double costo() {
        return litroDecorado.costo() + 1.00; // Costo del topping
    }
}
