package Vista;

import Controlador.ControladorPedido;
import Factory.LitrosFactory;
import Modelo.ConfiguracionAlcaldias;
import Modelo.Coordenadas;
import Modelo.Litro;
import Modelo.Decorator.ToppingAzucarMorena;
import Modelo.Decorator.ToppingFrutosRojos;
import Modelo.Decorator.ToppingHieloExtra;
import Modelo.Decorator.ToppingHierbabuena;
import Modelo.Decorator.ToppingRodajasLimon;
import Observer.Repartidor;
import Proxy.Cliente;
import Observer.NotificadorPedido;
import Singleton.GestionClientes;
import Strategy.ContextoEntrega;

import java.util.Scanner;

public class Main {
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
                        procesarPedidoRegular(scanner, controlador, notificador, cliente);
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

    private static void procesarPedidoRegular(Scanner scanner, ControladorPedido controlador, NotificadorPedido notificador, Cliente cliente) {
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
                    agregarLitro(scanner, controlador, notificador, "Mojito Tradicional");
                    break;
                case 2:
                    agregarLitro(scanner, controlador, notificador, "Mojito de Fresa");
                    break;
                case 3:
                    agregarLitro(scanner, controlador, notificador, "Azulito");
                    break;
                case 4:
                    agregarLitro(scanner, controlador, notificador, "Ultravioleta");
                    break;
                case 5:
                    agregarToppings(scanner, controlador, notificador);
                    break;
                case 6:
                    if (realizarCompra(controlador,cliente,notificador)) {
                        continuarPedido = false;
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
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
    Litro bebida = LitrosFactory.crearLitro(baseBebida); // Crear la bebida base

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
                case 1:
                    bebida = new ToppingHierbabuena(bebida);
                    break;
                case 2:
                    bebida = new ToppingRodajasLimon(bebida);
                    break;
                case 3:
                    bebida = new ToppingAzucarMorena(bebida);
                    break;
                case 4:
                    bebida = new ToppingFrutosRojos(bebida);
                    break;
                case 5:
                    bebida = new ToppingHieloExtra(bebida);
                    break;
            }
            System.out.println(toppingsDisponibles[opcionTopping - 1] + " agregado a la bebida.");
        } else if (opcionTopping == 6) {
            agregarToppings = false;
        } else {
            System.out.println("Opción no válida. Intenta nuevamente.");
        }
    }

    controlador.agregarLitro(bebida); // Agrega la bebida decorada
    notificador.notificar("Bebida personalizada añadida al pedido.");
}

    public static boolean realizarCompra(ControladorPedido controlador, Cliente cliente, NotificadorPedido notificador) {
        Scanner scanner = new Scanner(System.in);
    
        // Solicitar número de cuenta bancaria
        System.out.println("--- Pantalla de compra segura ---");
        System.out.print("Por favor, ingrese su número de cuenta bancaria para finalizar la compra: ");
        long numeroIngresado = scanner.nextLong();
    
        // Verificar si el número de cuenta bancaria ingresado coincide con el del cliente
        if (numeroIngresado == cliente.getCuentaBancaria()) {
            // Obtener el total de la compra
            double totalCompra = controlador.obtenerTotalPedido();
    
            // Verificar fondos disponibles
            if (cliente.getDineroDisponible() >= totalCompra) {
                // Descontar del saldo del cliente
                cliente.setDineroDisponible(cliente.getDineroDisponible() - totalCompra);
                System.out.println("Compra realizada con éxito. Total: $" + totalCompra);
                controlador.vaciarPedido(); // Limpiar el pedido después de realizar la compra
                return true;
            } else {
                double diferencia = totalCompra - cliente.getDineroDisponible();
                System.out.println("Fondos insuficientes. Necesitas $" + diferencia + " más.");
            }
        } else {
            System.out.println("El número de cuenta ingresado no coincide. Por favor, inténtelo nuevamente.");
        }
        return false;
    }
    
    public static void seguimientoPedido(Repartidor repartidor) {
        // Simular el proceso de entrega
        // Aquí puedes usar un hilo o timer para simular el tiempo que tardará en llegar el repartidor
        new Thread(() -> {
            try {
                // Simular el tiempo de preparación
                Thread.sleep(5000); // 5 segundos de preparación
                repartidor.actualizar("Preparando tu pedido...");
                Thread.sleep(5000); // 5 segundos en camino
                repartidor.actualizar("El repartidor está en camino...");
                Thread.sleep(5000); // 5 segundos hasta llegar
                repartidor.actualizar("El repartidor ha llegado a tu domicilio.");
                // Aquí puedes agregar una notificación final
                System.out.println("¡Tu pedido ha llegado! Sal a recogerlo.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }    
private static void crearCuentaNueva(Scanner scanner, GestionClientes gestionClientes) {
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

        // Obtener las coordenadas predeterminadas de la alcaldía
        Coordenadas ubicacion = ConfiguracionAlcaldias.obtenerCoordenadas(alcaldia);
        if (ubicacion == null) {
            System.out.println("Alcaldía no reconocida. Por favor intenta nuevamente.");
        } else {
            gestionClientes.registrarCliente(nuevoUsuario, nuevaContrasena, nombre, cuentaBancaria, dineroDisponible, edad, alcaldia);
            System.out.println("Cuenta creada exitosamente.");
        }
    }
}

}