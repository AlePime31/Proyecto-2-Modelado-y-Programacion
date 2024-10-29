package Modelo;

public class PedidoPreparando implements EstadoPedido {
    private Pedido pedido;

    public PedidoPreparando(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void siguienteEstado() {
        pedido.setEstadoPedido(new PedidoEntregado(pedido));
    }

    @Override
    public void mostrarEstado() {
        System.out.println("Pedido en preparación.");
    }
}
