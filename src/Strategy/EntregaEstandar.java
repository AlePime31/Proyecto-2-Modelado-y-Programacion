package Strategy;

public class EntregaEstandar implements EntregaEstrategia {
    @Override
    public void entregar() {
        System.out.println("Entrega estándar seleccionada. El pedido llegará en 40 minutos.");
    }
}
