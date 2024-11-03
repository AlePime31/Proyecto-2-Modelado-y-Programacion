package Modelo;

public class Vitrolero extends Litro {
    private String tipoMojito;
    private int capacidadLitros;

    public Vitrolero(String tipoMojito, int capacidadLitros) {
        this.tipoMojito = tipoMojito;
        this.capacidadLitros = capacidadLitros;
        this.descripcion = tipoMojito + " " + capacidadLitros + " litros"; // Define la descripción
    }

    public String getTipoMojito() {
        return tipoMojito;
    }

    public int getCapacidadLitros() {
        return capacidadLitros;
    }

    @Override
    public double costo() {
        return calcularCosto(); // Llama al método de costo específico
    }

    public double calcularCosto() {
        if (capacidadLitros == 10) {
            return 500; // precio para 10 litros
        } else if (capacidadLitros == 20) {
            return 900; // precio para 20 litros
        }
        return 0;
    }
}
