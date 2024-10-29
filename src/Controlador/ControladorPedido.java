package Controlador;

import Modelo.Litro;
import Modelo.LitroToppings;
import Modelo.Pedido;

public class ControladorPedido {
    private Pedido pedido;

    public ControladorPedido() {
        pedido = new Pedido();
    }

    public void agregarLitro(Litro litros) {
        pedido.agregarLitro(litros);
        System.out.println("Litro agregado: " + litros.getDescripcion());
    }

    public void mostrarPedido() {
        pedido.mostrarPedido();
    }

    public void cambiarEstado() {
        pedido.getEstadoPedido().siguienteEstado();
        pedido.getEstadoPedido().mostrarEstado();
    }
    public void eliminarLitro(Litro litros) {
        pedido.eliminarLitro(litros);
        System.out.println("Litro eliminado: " + litros.getDescripcion());
    }
    public void vaciarPedido() {
        pedido.vaciar();
        System.out.println("El carrito ha sido vaciado.");
    }
    public void agregarLitroToppings(LitroToppings litros) {
        // Aquí puedes agregar la lógica para añadir la pizza al pedido
        System.out.println("Litro añadida al pedido: " + litros);
    }
    public double obtenerTotalPedido() {
        return pedido.calcularTotal();  // Asegúrate de implementar este método en la clase Pedido
    }
    
}
