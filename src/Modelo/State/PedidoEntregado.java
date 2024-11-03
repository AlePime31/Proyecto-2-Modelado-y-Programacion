package Modelo.State;

public class PedidoEntregado implements EstadoPedido {
    public PedidoEntregado(Pedido pedido) {
    }

    @Override
    public void siguienteEstado() {
        System.out.println("El pedido ya ha sido entregado.");
    }

    @Override
    public void mostrarEstado() {
        System.out.println("Pedido entregado.");
    }
}
