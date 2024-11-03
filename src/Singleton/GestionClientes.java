package Singleton;

import Proxy.Cliente;
import java.util.ArrayList;
import java.util.List;

public class GestionClientes {
    private static GestionClientes instanciaUnica = null;
    private List<Cliente> listaClientes;
    
    public void registrarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }
    

    // Constructor privado para Singleton
    private GestionClientes() {
        listaClientes = new ArrayList<>();
        // Cargar clientes de ejemplo
        // Asegúrate de que el constructor de Cliente acepte Coordenadas
        listaClientes.add(new Cliente("usuario1", "12345", "Juan", 123456789, 10000, 20, "Coyoacan"));
        listaClientes.add(new Cliente("usuario2", "54321", "Maria", 987654321, 800, 24,"Tlalpan"));
    }

    // Método estático para obtener la única instancia de la clase
    public static GestionClientes obtenerInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new GestionClientes();
        }
        return instanciaUnica;
    }

    // Actualiza este método para aceptar Coordenadas
    public void registrarCliente(String nombreUsuario, String contrasena, String nombre, long cuentaBancaria, double dineroDisponible, int edad, String alcaldia) {
        Cliente nuevoCliente = new Cliente(nombreUsuario, contrasena, nombre, cuentaBancaria, dineroDisponible, edad, alcaldia);
        listaClientes.add(nuevoCliente);
    }
    

    public Cliente buscarClientePorNombreUsuario(String nombreUsuario) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombreUsuario().equals(nombreUsuario)) {
                return cliente;
            }
        }
        return null; // Si no encuentra al cliente
    }

    public boolean validarInicioSesion(String nombreUsuario, String contrasena) {
        Cliente cliente = buscarClientePorNombreUsuario(nombreUsuario);
        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            System.out.println("Inicio de sesión exitoso. Bienvenid@ a Dinamita Drinks, " + cliente.getNombre() + "!");
            return true;
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
            return false;
        }
    }
}
