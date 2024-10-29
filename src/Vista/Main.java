package Vista;

import Controlador.ControladorPedido;
import Factory.LitrosFactory;
import Modelo.Coordenadas;
import Modelo.Litro;
import Modelo.LitroToppings;
import Observer.Repartidor;
import Proxy.Cliente;
import Observer.NotificadorPedido;
import Singleton.GestionClientes;
import Strategy.ContextoEntrega;

import java.util.Scanner;

public class Main {
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
                case 1:
                    // Iniciar sesión
                    System.out.println("\n=== Inicio de sesión ===");
                    System.out.print("Nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String contrasena = scanner.nextLine();

                    if (gestionClientes.validarInicioSesion(nombreUsuario, contrasena)) {
                        Cliente cliente = gestionClientes.buscarClientePorNombreUsuario(nombreUsuario);
                        Coordenadas ubicacionCliente = cliente.getUbicacion();

                        boolean continuar = true;
                        while (continuar) {
                            System.out.println("\n=== Tipo de Pedido ===");
                            System.out.println("1. Pedido Regular");
                            System.out.println("2. Pedido de Evento");
                            System.out.println("3. Regresar");
                            System.out.print("Selecciona una opción: ");
                            int opcionTipoPedido = scanner.nextInt();
                            scanner.nextLine();

                            if (opcionTipoPedido == 1) {
                                // Menú de Pedido Regular
                                procesarPedidoRegular(scanner, controlador, notificador);
                            } else if (opcionTipoPedido == 2) {
                                // Menú de Pedido de Evento
                                procesarPedidoEvento(scanner, controlador, notificador);
                            } else {
                                continuar = false;
                            }
                        }
                    }
                    break;

                case 2:
                    // Crear cuenta nueva
                    crearCuentaNueva(scanner, gestionClientes);
                    break;

                case 3:
                    // Salir del programa
                    ejecutando = false;
                    System.out.println("Gracias por usar Mojitos. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
        scanner.close();
    }

    private static void procesarPedidoRegular(Scanner scanner, ControladorPedido controlador, NotificadorPedido notificador) {
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
                case 1:
                    Litro mojitoTradicional = LitrosFactory.crearLitro("Mojito Tradicional");
                    controlador.agregarLitro(mojitoTradicional);
                    notificador.notificar("Mojito Tradicional añadido al pedido.");
                    break;
                case 2:
                    Litro mojitoFresa = LitrosFactory.crearLitro("Mojito de Fresa");
                    controlador.agregarLitro(mojitoFresa);
                    notificador.notificar("Mojito de Fresa añadido al pedido.");
                    break;
                case 3:
                    Litro azulito = LitrosFactory.crearLitro("Azulito");
                    controlador.agregarLitro(azulito);
                    notificador.notificar("Azulito añadido al pedido.");
                    break;
                case 4:
                    Litro ultravioleta = LitrosFactory.crearLitro("Ultravioleta");
                    controlador.agregarLitro(ultravioleta);
                    notificador.notificar("Ultravioleta añadido al pedido.");
                    break;
                case 5:
                    LitroToppings bebidaPersonalizada = new LitroToppings();
                    System.out.print("Selecciona la base de la bebida (Mojito Tradicional, Azulito, Ultravioleta): ");
                    String baseBebida = scanner.nextLine();
                    bebidaPersonalizada.setBase(baseBebida);

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
                            String toppingSeleccionado = toppingsDisponibles[opcionTopping - 1];
                            bebidaPersonalizada.agregarTopping(toppingSeleccionado);
                            System.out.println(toppingSeleccionado + " añadido.");
                        } else if (opcionTopping == 6) {
                            agregarToppings = false;
                        } else {
                            System.out.println("Opción no válida. Intenta nuevamente.");
                        }
                    }
                    controlador.agregarLitroToppings(bebidaPersonalizada);
                    notificador.notificar(baseBebida + " personalizado añadido al pedido.");
                    break;

                case 6:
                    continuarPedido = false;
                    break;
            }
        }
    }

    private static void procesarPedidoEvento(Scanner scanner, ControladorPedido controlador, NotificadorPedido notificador) {
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
                continuarEvento = false;
            } else {
                System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }

    private static void crearCuentaNueva(Scanner scanner, GestionClientes gestionClientes) {
        System.out.println("\n=== Crear Cuenta Nueva ===");
        System.out.print("Nombre de usuario: ");
        String nuevoUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String nuevaContrasena = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        if (edad < 18) {
            System.out.println("Debes ser mayor de 18 años para crear una cuenta.");
        } else {
            System.out.print("Dirección (Latitud): ");
            double latitud = scanner.nextDouble();
            System.out.print("Dirección (Longitud): ");
            double longitud = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer

            Coordenadas ubicacion = new Coordenadas(latitud, longitud);
            Cliente nuevoCliente = new Cliente(nuevoUsuario, nuevaContrasena, nombre, edad, longitud, edad, ubicacion);
            gestionClientes.registrarCliente(nuevoCliente);
            System.out.println("Cuenta creada exitosamente.");
        }
    }
    
    
}
