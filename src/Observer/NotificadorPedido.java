package Observer;

import java.util.ArrayList;
import java.util.List;

public class NotificadorPedido {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void notificar(String estadoPedido) {
        for (Observador observador : observadores) {
            observador.actualizar(estadoPedido);
        }
    }
}
