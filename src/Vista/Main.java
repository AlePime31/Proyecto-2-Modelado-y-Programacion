package Vista;

import Controlador.ControladorPedido;
import Factory.LitrosFactory;
import Modelo.ConfiguracionAlcaldias;
import Modelo.Coordenadas;
import Modelo.Litro;
import Modelo.Decorator.*;
import Observer.Repartidor;
import Proxy.Cliente;
import Observer.NotificadorPedido;
import Singleton.GestionClientes;
import Strategy.ContextoEntrega;
import Strategy.EntregaEstandar;
import Strategy.EntregaRapida;

import java.util.Scanner;

public class Main {
    // Constantes para opciones de menú
    private static final int OPCION_INICIAR_SESION = 1;
    private static final int OPCION_CREAR_CUENTA = 2;
    private static final int OPCION_SALIR = 3;
    private static final int OPCION_PEDIDO_REGULAR = 1;
    private static final int OPCION_PEDIDO_EVENTO = 2;
    private static final int OPCION_REGRESAR = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControladorPedido controlador = new ControladorPedido();
        GestionClientes gestionClientes = GestionClientes.obtenerInstancia();
        NotificadorPedido notificador = new NotificadorPedido();
        ContextoEntrega contextoEntrega = new ContextoEntrega();

        Coordenadas ubicacionTienda = new Coordenadas(0.0, 0.0);
        Repartidor repartidor1 = new Repartidor("Carlos", ubicacionTienda);
        notificador.agregarObservador(repartidor1);

        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Crear Cuenta Nueva");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcionMenu = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcionMenu) {
                case OPCION_INICIAR_SESION:
                    iniciarSesion(scanner, controlador, notificador, gestionClientes);
                    break;
                case OPCION_CREAR_CUENTA:
                    crearCuentaNueva(scanner, gestionClientes);
                    break;
                case OPCION_SALIR:
                    ejecutando = false;
                    System.out.println("Gracias por usar Mojitos. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
        scanner.close();
    }

    private static void iniciarSesion(Scanner scanner, ControladorPedido controlador, NotificadorPedido notificador, GestionClientes gestionClientes) {
        System.out.println("\n=== Inicio de sesión ===");
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
    
        if (gestionClientes.validarInicioSesion(nombreUsuario, contrasena)) {
            Cliente cliente = gestionClientes.buscarClientePorNombreUsuario(nombreUsuario);
            Coordenadas ubicacionCliente = cliente.getUbicacion();
    
            boolean continuar = true;
            ContextoEntrega contextoEntrega = new ContextoEntrega();  // Crea un nuevo contexto de entrega
    
            while (continuar) {
                System.out.println("\n=== Tipo de Pedido ===");
                System.out.println("1. Pedido Regular");
                System.out.println("2. Pedido de Evento");
                System.out.println("3. Regresar");
                System.out.print("Selecciona una opción: ");
                int opcionTipoPedido = scanner.nextInt();
                scanner.nextLine();
    
                switch (opcionTipoPedido) {
                    case OPCION_PEDIDO_REGULAR:
                        procesarPedidoRegular(scanner, controlador, notificador, cliente, contextoEntrega);  // Pasar contextoEntrega
                        break;
                    case OPCION_PEDIDO_EVENTO:
                        procesarPedidoEvento(scanner, controlador, notificador, cliente);
                        break;
                    case OPCION_REGRESAR:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }
        }
    }
    

    private static void procesarPedidoRegular(Scanner scanner, ControladorPedido controlador, NotificadorPedido notificador, Cliente cliente, ContextoEntrega contextoEntrega) {
        boolean continuarPedido = true;
        while (continuarPedido) {
            System.out.println("\n=== Menú de Pedido Regular ===");
            System.out.println("1. Agregar Mojito Tradicional");
            System.out.println("2. Agregar Mojito de Fresa");
            System.out.println("3. Agregar Azulito");
            System.out.println("4. Agregar Ultravioleta");
            System.out.println("5. Agregar Toppings");
            System.out.println("6. Finalizar Pedido");
            System.out.print("Selecciona una opción: ");
            int opcionPedido = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcionPedido) {
                case 1 -> agregarLitro(scanner, controlador, notificador, "Mojito Tradicional");
                case 2 -> agregarLitro(scanner, controlador, notificador, "Mojito de Fresa");
                case 3 -> agregarLitro(scanner, controlador, notificador, "Azulito");
                case 4 -> agregarLitro(scanner, controlador, notificador, "Ultravioleta");
                case 5 -> agregarToppings(scanner, controlador, notificador);
                case 6 -> {
                    if (realizarCompra(controlador, cliente, notificador)) {
                        continuarPedido = false; // Termina el flujo si la compra se realiza con éxito
                    }
                }
                default -> System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }

    private static void procesarPedidoEvento(Scanner scanner, ControladorPedido controlador, NotificadorPedido notificador, Cliente cliente) {
        boolean continuarEvento = true;
        while (continuarEvento) {
            System.out.println("\n=== Menú de Pedido para Evento ===");
            System.out.println("1. Agregar Vitrolero de Mojito Tradicional");
            System.out.println("2. Agregar Vitrolero de Mojito de Fresa");
            System.out.println("3. Agregar Vitrolero de Azulito");
            System.out.println("4. Agregar Vitrolero de Ultravioleta");
            System.out.println("5. Finalizar Pedido de Evento");
            System.out.print("Selecciona una opción: ");
            int opcionVitrolero = scanner.nextInt();
            scanner.nextLine();

            if (opcionVitrolero >= 1 && opcionVitrolero <= 4) {
                System.out.print("Selecciona el tamaño del vitrolero (10 o 20 litros): ");
                int tamaño = scanner.nextInt();
                scanner.nextLine();
                if (tamaño == 10 || tamaño == 20) {
                    String tipo = switch (opcionVitrolero) {
                        case 1 -> "Mojito Tradicional";
                        case 2 -> "Mojito de Fresa";
                        case 3 -> "Azulito";
                        case 4 -> "Ultravioleta";
                        default -> throw new IllegalStateException("Unexpected value: " + opcionVitrolero);
                    };
                    Litro vitrolero = LitrosFactory.crearLitroEvento(tipo, tamaño);
                    controlador.agregarLitro(vitrolero);
                    notificador.notificar(tipo + " de " + tamaño + " litros añadido al pedido.");
                } else {
                    System.out.println("Tamaño no válido. Solo se permite 10 o 20 litros.");
                }
            } else if (opcionVitrolero == 5) {
                if (realizarCompra(controlador, cliente, notificador)) {
                    continuarEvento = false;
                }
            } else {
                System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }

    private static void agregarLitro(Scanner scanner, ControladorPedido controlador, NotificadorPedido notificador, String tipo) {
        Litro litro = LitrosFactory.crearLitro(tipo);
        controlador.agregarLitro(litro);
        notificador.notificar(tipo + " añadido al pedido.");
    }

    private static void agregarToppings(Scanner scanner, ControladorPedido controlador, NotificadorPedido notificador) {
        System.out.print("Selecciona la base de la bebida (Mojito Tradicional, Azulito, Ultravioleta): ");
        String baseBebida = scanner.nextLine();
        Litro bebida = LitrosFactory.crearLitro(baseBebida);

        String[] toppingsDisponibles = {"Hierbabuena", "Rodajas de Limón", "Azúcar morena", "Frutos rojos", "Hielo extra"};
        boolean agregarToppings = true;

        while (agregarToppings) {
            System.out.println("\n=== Selección de Toppings ===");
            for (int i = 0; i < toppingsDisponibles.length; i++) {
                System.out.println((i + 1) + ". " + toppingsDisponibles[i]);
            }
            System.out.println("6. Terminar selección de toppings");
            System.out.print("Selecciona un topping por su número (1-5), o 6 para terminar: ");
            int opcionTopping = scanner.nextInt();
            scanner.nextLine();

            if (opcionTopping >= 1 && opcionTopping <= 5) {
                switch (opcionTopping) {
                    case 1 -> bebida = new ToppingHierbabuena(bebida);
                    case 2 -> bebida = new ToppingRodajasLimon(bebida);
                    case 3 -> bebida = new ToppingAzucarMorena(bebida);
                    case 4 -> bebida = new ToppingFrutosRojos(bebida);
                    case 5 -> bebida = new ToppingHieloExtra(bebida);
                }
                System.out.println(toppingsDisponibles[opcionTopping - 1] + " añadido.");
            } else if (opcionTopping == 6) {
                agregarToppings = false;
            } else {
                System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
        controlador.agregarLitro(bebida);
        notificador.notificar("Bebida con toppings añadida al pedido.");
    }
    public static boolean realizarCompra(ControladorPedido controlador, Cliente cliente, NotificadorPedido notificador) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("--- Pantalla de compra segura ---");
        System.out.print("Por favor, ingrese su número de cuenta bancaria para finalizar la compra: ");
        long numeroIngresado = scanner.nextLong();
    
        if (numeroIngresado == cliente.getCuentaBancaria()) {
            double totalCompra = controlador.obtenerTotalPedido();
    
            if (cliente.getDineroDisponible() >= totalCompra) {
                cliente.setDineroDisponible(cliente.getDineroDisponible() - totalCompra);
                System.out.printf("Compra realizada con éxito. Total: $%.2f%n", totalCompra);
    
                ContextoEntrega contextoEntrega = new ContextoEntrega();
                boolean opcionValida = false;
    
                while (!opcionValida) {
                    System.out.println("Selecciona el tipo de entrega:");
                    System.out.println("1. Entrega Rápida (20 minutos)");
                    System.out.println("2. Entrega Estándar (40 minutos)");
                    int opcionEntrega = scanner.nextInt();
    
                    switch (opcionEntrega) {
                        case 1 -> {
                            contextoEntrega.setEstrategia(new EntregaRapida());
                            opcionValida = true;
                        }
                        case 2 -> {
                            contextoEntrega.setEstrategia(new EntregaEstandar());
                            opcionValida = true;
                        }
                        default -> System.out.println("Opción no válida. Por favor, selecciona una opción correcta.");
                    }
                }
    
                contextoEntrega.entregarPedido();
                controlador.iniciarSeguimiento();
                notificador.notificar("Pedido completado.");
    
                // Llamar a vaciarCarrito al final para limpiar todo
                controlador.vaciarCarrito();
    
                return true;
            } else {
                System.out.printf("Fondos insuficientes. Necesitas $%.2f más.%n", totalCompra - cliente.getDineroDisponible());
            }
        } else {
            System.out.println("El número de cuenta ingresado no coincide. Por favor, inténtelo nuevamente.");
        }
        return false;
    }
    
    
    
 // Método corregido:
public static void crearCuentaNueva(Scanner scanner, GestionClientes gestionClientes) {
    System.out.println("\n=== Crear Cuenta Nueva ===");
    System.out.print("Nombre de usuario: ");
    String nuevoUsuario = scanner.nextLine();
    System.out.print("Contraseña: ");
    String nuevaContrasena = scanner.nextLine();
    System.out.print("Nombre completo: ");
    String nombre = scanner.nextLine();
    System.out.print("Número de cuenta bancaria: ");
    long cuentaBancaria = scanner.nextLong();
    System.out.print("Dinero disponible en la cuenta: ");
    double dineroDisponible = scanner.nextDouble();
    System.out.print("Edad: ");
    int edad = scanner.nextInt();
    scanner.nextLine(); // Limpiar el buffer

    if (edad < 18) {
        System.out.println("Debes ser mayor de 18 años para crear una cuenta.");
    } else {
        System.out.print("Selecciona tu alcaldía (Ej. Tlalpan, Coyoacan): ");
        String alcaldia = scanner.nextLine();

        // Aquí solo pasas el nombre de la alcaldía, no las coordenadas
        if (ConfiguracionAlcaldias.obtenerCoordenadas(alcaldia) == null) {
            System.out.println("Alcaldía no reconocida. Por favor intenta nuevamente.");
        } else {
            gestionClientes.registrarCliente(nuevoUsuario, nuevaContrasena, nombre, cuentaBancaria, dineroDisponible, edad, alcaldia);
            System.out.println("Cuenta creada exitosamente.");
        }
    }
}

}
