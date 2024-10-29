
package Modelo;

public class Vitrolero {
    private String tipoMojito;
    private int capacidadLitros; // 10 o 20 litros

    public Vitrolero(String tipoMojito, int capacidadLitros) {
        this.tipoMojito = tipoMojito;
        this.capacidadLitros = capacidadLitros;
    }

    public String getTipoMojito() {
        return tipoMojito;
    }

    public int getCapacidadLitros() {
        return capacidadLitros;
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
