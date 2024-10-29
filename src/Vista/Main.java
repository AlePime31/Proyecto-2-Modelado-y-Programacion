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

        // Coordenadas de la tienda de mojitos
        Coordenadas ubicacionTienda = new Coordenadas(0.0, 0.0);

        // Añadir repartidores como observadores del estado del pedido
        Repartidor repartidor1 = new Repartidor("Carlos", ubicacionTienda);

        notificador.agregarObservador(repartidor1);

        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Crear Cuenta Nueva");
            System.out.print("Selecciona una opción: ");
            int opcionMenu = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcionMenu) {
                case 1: // Iniciar Sesión
                    System.out.println("\n=== Inicio de sesión ===");
                    System.out.print("Nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String contrasena = scanner.nextLine();

                    if (gestionClientes.validarInicioSesion(nombreUsuario, contrasena)) {
                        Cliente cliente = gestionClientes.buscarClientePorNombreUsuario(nombreUsuario);
                        Coordenadas ubicacionCliente = cliente.getUbicacion(); // Asegúrate de que esto sea Coordenadas
                        
                        // Menú de opciones para crear un pedido
                        boolean continuar = true;
                        while (continuar) {
                            // Primer menú: Menú de Pedido
                            System.out.println("\n=== Menú de Pedido ===");
                            System.out.println("1. Agregar Mojito Tradicional");
                            System.out.println("2. Agregar Mojito de Fresa");
                            System.out.println("3. Agregar Azulito");
                            System.out.println("4. Agregar Ultravioleta");
                            System.out.println("5. Agregar toppings");
                            System.out.println("6. Continuar");
                            System.out.print("Selecciona una opción: ");
                            int opcionPedido = scanner.nextInt();
                            scanner.nextLine();  // Limpiar el buffer
                        
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
                                        scanner.nextLine(); // Limpiar el buffer
                        
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
                                    // Aquí pasamos al segundo menú de compra
                                    boolean enMenuCompra = true;
                                    while (enMenuCompra) {
                                        System.out.println("\n=== Menú de Compra ===");
                                        System.out.println("1. Realizar Compra");
                                        System.out.println("2. Cancelar Compra");
                                        System.out.print("Selecciona una opción: ");
                                        int opcionCompra = scanner.nextInt();
                                        scanner.nextLine();  // Limpiar el buffer
                        
                                        switch (opcionCompra) {
                                            case 1:
                                                // Llamar al método realizarCompra que validará la compra
                                                realizarCompra(controlador, cliente, notificador, repartidor1);
                                                
                                                // Si la compra se realiza correctamente, pasar al menú de opciones del pedido
                                                boolean enMenuOpciones = true;
                                                while (enMenuOpciones) {
                                                    System.out.println("\n=== Opciones del Pedido ===");
                                                    System.out.println("6. Ver Pedido");
                                                    System.out.println("7. Cambiar Estado del Pedido");
                                                    System.out.println("8. Entregar Pedido");
                                                    System.out.println("9. Salir");
                                                    System.out.print("Selecciona una opción: ");
                                                    int opcionOpciones = scanner.nextInt();
                                                    scanner.nextLine();  // Limpiar el buffer
                        
                                                    switch (opcionOpciones) {
                                                        case 6:
                                                            controlador.mostrarPedido();
                                                            break;
                                                        case 7:
                                                            controlador.cambiarEstado();
                                                            notificador.notificar("Estado del pedido actualizado.");
                                                            break;
                                                        case 8:
                                                            System.out.println("Iniciando la entrega...");
                                                            repartidor1.entregarPedido(ubicacionCliente); // Pasa las coordenadas del cliente
                                                            break;
                                                        case 9:
                                                            enMenuOpciones = false;  // Salir del menú de opciones del pedido
                                                            enMenuCompra = false;  // Salir del menú de compra
                                                            continuar = false;  // Salir completamente del proceso de compra
                                                            System.out.println("Gracias por usar Mojitos. ¡Hasta luego!");
                                                            break;
                                                        default:
                                                            System.out.println("Opción no válida. Intenta nuevamente.");
                                                    }
                                                }
                                                break;
                        
                                            case 2:
                                                // Cancelar la compra y volver al menú de pedido
                                                controlador.vaciarPedido(); // Vacía el carrito si la compra se cancela
                                                System.out.println("Compra cancelada. Carrito vaciado.");
                                                enMenuCompra = false; // Salir del menú de compra y regresar al menú de pedido
                                                break;
                        
                                            default:
                                                System.out.println("Opción no válida. Intenta nuevamente.");
                                        }
                                    }
                                    break;
                                    default:
                                    System.out.println("Opción no válida. Intenta nuevamente.");
                            }
                        }
                }
                case 2: // Crear Cuenta Nueva
                System.out.println("\n=== Crear Cuenta Nueva ===");
                System.out.print("Nombre de usuario: ");
                String nuevoUsuario = scanner.nextLine();
                System.out.print("Contraseña: ");
                String nuevaContrasena = scanner.nextLine();
                System.out.print("Nombre completo: ");
                String nombre = scanner.nextLine();
                System.out.print("Edad: ");
                int edad = scanner.nextInt();
                scanner.nextLine();  // Limpiar buffer
                
                if (edad < 18) {
                    System.out.println("Debes ser mayor de 18 años para crear una cuenta.");
                } else {
                    System.out.print("Dirección (Latitud): ");
                    double latitud = scanner.nextDouble();
                    System.out.print("Dirección (Longitud): ");
                    double longitud = scanner.nextDouble();
                    scanner.nextLine();  // Limpiar buffer

                    Coordenadas direccionCliente = new Coordenadas(latitud, longitud);
                    gestionClientes.registrarCliente(nuevoUsuario, nuevaContrasena, nombre, edad, longitud, edad, direccionCliente);
                    System.out.println("Cuenta creada exitosamente.");
                }
                break;

            case 3: // Salir
                ejecutando = false;
                System.out.println("Gracias por usar Mojitos. ¡Hasta luego!");
                break;

            default:
                System.out.println("Opción no válida. Intenta nuevamente.");
        }
    }

    scanner.close();
}


    // Implementación de realizarCompra
    public static void realizarCompra(ControladorPedido controlador, Cliente cliente, NotificadorPedido notificador, Repartidor repartidor) {
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
                
                // Asignar un repartidor al pedido
                notificador.notificar("Tu pedido ha sido asignado a " + repartidor.getNombre() + ".");
                // Iniciar el seguimiento del estado del pedido
                seguimientoPedido(repartidor);
                
                controlador.vaciarPedido(); // Limpiar el pedido después de realizar la compra
            } else {
                double diferencia = totalCompra - cliente.getDineroDisponible();
                System.out.println("Fondos insuficientes. Necesitas $" + diferencia + " más.");
            }
        } else {
            System.out.println("El número de cuenta ingresado no coincide. Por favor, inténtelo nuevamente.");
            realizarCompra(controlador, cliente, notificador, repartidor); // Volver a intentar la compra
        }
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
    
}
