package Modelo.State;

public class PedidoEntregado implements EstadoPedido {
    private Pedido pedido;

    public PedidoEntregado(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void siguienteEstado() {
        System.out.println("El pedido ya ha sido entregraaado.");
    }

    @Override
    public void mostrarEstado() {
        System.out.println("Pedido entregado.");
    }
}
