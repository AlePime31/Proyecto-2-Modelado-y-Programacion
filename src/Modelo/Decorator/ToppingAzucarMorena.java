package Modelo.Decorator;

import Modelo.Litro;

public class ToppingAzucarMorena extends LitroDecorator {
        public ToppingAzucarMorena(Litro litro) {
            super(litro);
        }
    
        @Override
        public String getDescripcion() {
            return litroDecorado.getDescripcion() + ", con Azucar Morena";
        }
    
        @Override
        public double costo() {
            return litroDecorado.costo() + 1.00; // Costo del topping
        }
    }
    