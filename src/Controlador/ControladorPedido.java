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
public Litro obtenerLitroPorIndice(int indice) {
    if (indice >= 0 && indice < pedido.getListaLitros().size()) {
        return pedido.getListaLitros().get(indice);
    }
    return null;
}

public void actualizarLitro(int indice, Litro litroActualizado) {
    if (indice >= 0 && indice < pedido.getListaLitros().size()) {
        pedido.getListaLitros().set(indice, litroActualizado);
    }
}
public void mostrarPedidoConIndices() {
    // Usamos la lista de litros que está dentro del objeto pedido
    if (pedido.getListaLitros().isEmpty()) {
        System.out.println("No hay bebidas en el pedido.");
        return;
    }

    System.out.println("\n=== Pedido Actual ===");
    double total = 0.0;
    // Accedemos a la lista de litros a través de pedido
    for (int i = 0; i < pedido.getListaLitros().size(); i++) {
        Litro bebida = pedido.getListaLitros().get(i);
        System.out.println((i + 1) + ". " + bebida.getDescripcion() + " - $" + bebida.costo());
        total += bebida.costo();
    }
    System.out.println("Total: $" + total);
}




    public double obtenerTotalPedido() {
        return pedido.calcularTotal();  // Asegúrate de implementar este método en la clase Pedido
    }

    // Método para iniciar el seguimiento del pedido
    public void iniciarSeguimiento() {
        pedido.seguimientoPedido();  // Esto llama al método de seguimiento de estados
    }
}
