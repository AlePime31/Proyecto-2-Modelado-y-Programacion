package Strategy;

public class ContextoEntrega {
    private EntregaEstrategia estrategia;

    public void setEstrategia(EntregaEstrategia estrategia) {
        this.estrategia = estrategia;
    }

    public void entregarPedido() {
        estrategia.entregar();
    }
}
