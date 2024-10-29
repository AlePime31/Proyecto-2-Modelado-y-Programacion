package Modelo;

public class PedidoPendiente implements EstadoPedido {
    private Pedido pedido;

    public PedidoPendiente(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void siguienteEstado() {
        pedido.setEstadoPedido(new PedidoPreparando(pedido));
    }

    @Override
    public void mostrarEstado() {
        System.out.println("Pedido pendiente.");
    }
}
