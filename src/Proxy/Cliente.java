package Proxy;

import Modelo.Coordenadas;
import Modelo.ConfiguracionAlcaldias;

public class Cliente {
    private String nombreUsuario;
    private String contrasena;
    private String nombre;
    private long cuentaBancaria;
    private double dineroDisponible;
    private int edad;
    private String alcaldia; // Nueva variable para la alcaldía
    private Coordenadas ubicacion; 

    // Constructor actualizado
    public Cliente(String nombreUsuario, String contrasena, String nombre, long cuentaBancaria, double dineroDisponible, int edad, String alcaldia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.edad = edad;
        this.alcaldia = alcaldia;
        this.ubicacion = ConfiguracionAlcaldias.obtenerCoordenadas(alcaldia); // Usar coordenadas de ConfiguracionAlcaldias
        this.cuentaBancaria = cuentaBancaria;
        this.dineroDisponible = dineroDisponible;
    }

    // Getters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public Coordenadas getUbicacion() {
        return ubicacion; // Asegúrate de que esto retorne Coordenadas
    }

    public int getEdad() {
        return edad;
    }
    public long getCuentaBancaria() {
        return this.cuentaBancaria;
    }
    
    public double getDineroDisponible() {
        return this.dineroDisponible;
    }
    
    public void setDineroDisponible(double dineroDisponible) {
        this.dineroDisponible = dineroDisponible;
    }
    public String getAlcaldia() {
        return alcaldia;
    }
    // Agregar este método en la clase Cliente
public boolean verificarCuentaBancaria(long cuentaIngresada) {
    return this.cuentaBancaria == cuentaIngresada;
}

    
}
