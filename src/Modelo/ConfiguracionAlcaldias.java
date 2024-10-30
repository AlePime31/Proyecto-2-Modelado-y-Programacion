package Modelo;

import java.util.HashMap;
import java.util.Map;

public class ConfiguracionAlcaldias {
    private static final Map<String, Coordenadas> coordenadasAlcaldias = new HashMap<>();

    static {
        coordenadasAlcaldias.put("Tlalpan", new Coordenadas(19.4326, -99.1332));
        coordenadasAlcaldias.put("Coyoacan", new Coordenadas(19.4065, -99.1625));
        // Agrega más alcaldías con sus respectivas coordenadas predeterminadas
    }

    public static Coordenadas obtenerCoordenadas(String alcaldia) {
        return coordenadasAlcaldias.get(alcaldia);
    }
}
