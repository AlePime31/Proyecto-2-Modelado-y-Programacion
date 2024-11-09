package Modelo;

import java.util.HashMap;
import java.util.Map;

public class ConfiguracionAlcaldias {
    private static final Map<String, Coordenadas> coordenadasAlcaldias = new HashMap<>();

    static {
        coordenadasAlcaldias.put("Tlalpan", new Coordenadas(19.4326, -99.1332));
        coordenadasAlcaldias.put("Coyoacan", new Coordenadas(19.4065, -99.1625));
        coordenadasAlcaldias.put("Iztapalapa", new Coordenadas(19.842, -99.2123));
        coordenadasAlcaldias.put("Miguel Hidalgo", new Coordenadas(19.4332, -99.1911));
        coordenadasAlcaldias.put("Venustiano Carranza", new Coordenadas(19.4215, -99.1416));
        coordenadasAlcaldias.put("Azcapotzalco", new Coordenadas(19.5000, -99.2000));
        coordenadasAlcaldias.put("Xochimilco", new Coordenadas(19.2500, -99.1000));
        coordenadasAlcaldias.put("Gustavo A. Madero", new Coordenadas(19.5075, -99.1381));
        coordenadasAlcaldias.put("Benito Juarez", new Coordenadas(19.4000, -99.1700));
    }

    public static Coordenadas obtenerCoordenadas(String alcaldia) {
        return coordenadasAlcaldias.get(alcaldia);
    }
}
