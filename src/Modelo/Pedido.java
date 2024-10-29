package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Litro> listaLitros;
    private EstadoPedido estadoPedido; // Maneja el estado del pedido (En preparación, En camino, Entregado, etc.)
    public Pedido() {
        listaLitros = new ArrayList<>();
        estadoPedido = new PedidoPendiente(this); // Estado inicial correcto
    }
    
    public void agregarLitro(Litro litro) {
        listaLitros.add(litro);
    }
    public void agregarVitrolero(Vitrolero vitrolero) {
        // Lógica para agregar el vitrolero al pedido
        System.out.println("Añadiendo " + vitrolero.getTipoMojito() + " de " + vitrolero.getCapacidadLitros() + " litros al pedido.");
        // Puedes manejarlo similar a agregar Litro
    }
    
    
    public void mostrarPedido() {
        System.out.println("\n=== Pedido Actual ===");
        if (listaLitros.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            double total = 0.0;
            for (Litro litro : listaLitros) {
                System.out.println(litro.getDescripcion() + " - $" + litro.costo());
                total += litro.costo();
            }
            System.out.println("Total: $" + total);
        }
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
    public void eliminarLitro(Litro litro) {
        if (listaLitros.contains(litro)) {
            listaLitros.remove(litro);
            System.out.println(litro.getDescripcion() + " ha sido eliminado del pedido.");
        } else {
            System.out.println(litro.getDescripcion() + " no está en el pedido.");
        }
    }
    public void vaciar() {
        listaLitros.clear();
    }
    public double calcularTotal() {
        double total = 0.0;
        for (Litro litro : listaLitros) {
            total += litro.costo();
        }
        return total;
    }
    
    
}
