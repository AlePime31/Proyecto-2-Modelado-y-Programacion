package Modelo;

public class Coordenadas {
    private double latitud;
    private double longitud;

    public Coordenadas(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    // Método para calcular la distancia entre dos coordenadas
    public double calcularDistancia(Coordenadas otro) {
        // Implementa tu lógica de cálculo de distancia aquí (puedes usar la fórmula de Haversine, por ejemplo)
        double distancia = Math.sqrt(Math.pow(this.latitud - otro.latitud, 2) + Math.pow(this.longitud - otro.longitud, 2)); // Distancia simple
        return distancia; // Retorna la distancia calculada
    }
}
