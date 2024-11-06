package Modelo.State;

public class PedidoPendiente implements EstadoPedido {
    private Pedido pedido;

    public PedidoPendiente(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void siguienteEstado() {
        // Cambiar el estado a "Pedido en preparación"
        pedido.setEstadoPedido(new PedidoPreparando(pedido));
    }

    @Override
    public void mostrarEstado() {
        System.out.println("Pedido pendiente.");
    }
}
