package Modelo;

public abstract class Litro {
    protected String descripcion = "Litros";

    public String getDescripcion() {
        return descripcion;
    }

    public abstract double costo();
    
}
