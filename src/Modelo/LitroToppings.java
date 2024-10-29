package Modelo;

import java.util.ArrayList;
import java.util.List;

public class LitroToppings {
    private String base;  // Por ejemplo, puede ser el tipo de alcohol o jugo que se usará.
    private List<String> toppings;  // Lista de toppings adicionales que el cliente puede agregar.

    public LitroToppings() {
        toppings = new ArrayList<>();
    }

    // Configura la base (tipo de alcohol o sabor de la bebida)
    public void setBase(String base) {
        this.base = base;
    }

    // Agrega toppings adicionales a la bebida
    public void agregarTopping(String topping) {
        toppings.add(topping);
    }

    @Override
    public String toString() {
        return "Bebida personalizada con base " + base + " y toppings: " + toppings;
    }
}
