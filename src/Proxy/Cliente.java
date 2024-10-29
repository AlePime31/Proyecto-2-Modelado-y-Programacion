package Proxy;

import Modelo.Coordenadas;

public class Cliente {
    private String nombreUsuario;
    private String contrasena;
    private String nombre;
    private long cuentaBancaria;
    private double dineroDisponible;
    private int edad; // Cambia a int para la edad
    private Coordenadas ubicacion; // Cambia a Coordenadas

    // Constructor
    public Cliente(String nombreUsuario, String contrasena, String nombre, long cuentaBancaria, double dineroDisponible, int edad, Coordenadas ubicacion) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.edad = edad; // Agregar edad
        this.ubicacion = ubicacion; // Agregar ubicación
        this.cuentaBancaria=cuentaBancaria;
        this.dineroDisponible=dineroDisponible;
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
    
}
