package Observer;

import Modelo.Coordenadas;

public class Repartidor implements Observador {
    private String nombre;
    private Coordenadas ubicacion;

    public Repartidor(String nombre, Coordenadas ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }
    public String getNombre() {
        return nombre;
    }


    @Override
    public void actualizar(String estadoPedido) {
        System.out.println("Repartidor " + nombre + ": El pedido está " + estadoPedido);
    }

    // Método para entregar el pedido
    public void entregarPedido(Coordenadas coordenadasCliente) {
        // Aquí puedes implementar la lógica de entrega, por ejemplo:
        System.out.println("Repartidor " + nombre + " está entregando el pedido a: " + coordenadasCliente);
    }

    public double calcularDistancia(Coordenadas coordenadasCliente) {
        return ubicacion.calcularDistancia(coordenadasCliente);
    }
    public void entregarPedidoSimulado(Coordenadas coordenadasCliente) {
        double distancia = calcularDistancia(coordenadasCliente);
        while (distancia > 0.01) { // Mientras no esté cerca
            System.out.println("Repartidor " + nombre + " está a " + distancia + " km del cliente.");
            // Simula avanzar en la entrega
            avanzarHaciaCliente(coordenadasCliente);
            distancia = calcularDistancia(coordenadasCliente);
        }
        System.out.println("Repartidor " + nombre + " ha llegado. ¡Sal por tu pedido!");
    }
    
    // Método para simular el avance hacia el cliente
    private void avanzarHaciaCliente(Coordenadas coordenadasCliente) {
        double nuevaLatitud = ubicacion.getLatitud() + (coordenadasCliente.getLatitud() - ubicacion.getLatitud()) * 0.1;
        double nuevaLongitud = ubicacion.getLongitud() + (coordenadasCliente.getLongitud() - ubicacion.getLongitud()) * 0.1;
        ubicacion = new Coordenadas(nuevaLatitud, nuevaLongitud);
    }
    
}
