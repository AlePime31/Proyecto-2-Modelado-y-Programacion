package Controlador;

import Modelo.Litro;
import Modelo.State.Pedido;
import Modelo.State.PedidoPendiente;

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
    // En ControladorPedido
    public void vaciarCarrito() {
    pedido.vaciar(); // Vacía los productos en el pedido
    pedido.setEstadoPedido(new PedidoPendiente(pedido)); // Reinicia el estado a pendiente
    System.out.println("El carrito y los datos del pedido han sido reiniciados.");
}


    public double obtenerTotalPedido() {
        return pedido.calcularTotal();  // Asegúrate de implementar este método en la clase Pedido
    }

    // Método para iniciar el seguimiento del pedido
    public void iniciarSeguimiento() {
        pedido.seguimientoPedido();  // Esto llama al método de seguimiento de estados
    }
}
