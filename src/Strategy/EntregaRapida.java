package Strategy;

public class EntregaRapida implements EntregaEstrategia {
    @Override
    public void entregar() {
        System.out.println("Entrega rápida seleccionada. El pedido llegará en 20 minutos.");
    }
}
