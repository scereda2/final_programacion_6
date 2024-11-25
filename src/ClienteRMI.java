import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteRMI {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServicioRemoto servicio = (ServicioRemoto) registry.lookup("ServicioRemoto");
            Scanner scanner = new Scanner(System.in);
            boolean continuar = true;

            while (continuar) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Enviar mensaje al servidor");
                System.out.println("2. Ejecutar tarea en el servidor");
                System.out.println("3. Enviar otro mensaje al servidor");
                System.out.println("4. Ejecutar otra tarea en el servidor");
                System.out.println("5. Salir");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        System.out.println("Escriba su mensaje:");
                        String mensaje1 = scanner.nextLine();
                        Thread hiloMensaje1 = new Thread(() -> {
                            try {
                                String respuesta = servicio.enviarMensaje(mensaje1);
                                System.out.println("Respuesta del servidor (mensaje): " + respuesta);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        hiloMensaje1.start();
                        hiloMensaje1.join();
                        break;

                    case 2:
                        Thread hiloTarea1 = new Thread(() -> {
                            try {
                                String respuestaTarea = servicio.ejecutarTarea();
                                System.out.println("Respuesta del servidor (tarea): " + respuestaTarea);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        hiloTarea1.start();
                        hiloTarea1.join();
                        break;

                    case 3:
                        System.out.println("Escriba su mensaje:");
                        String mensaje2 = scanner.nextLine();
                        Thread hiloMensaje2 = new Thread(() -> {
                            try {
                                String respuesta = servicio.enviarMensaje(mensaje2);
                                System.out.println("Respuesta del servidor (mensaje): " + respuesta);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        hiloMensaje2.start();
                        hiloMensaje2.join();
                        break;

                    case 4:
                        Thread hiloTarea2 = new Thread(() -> {
                            try {
                                String respuestaTarea = servicio.ejecutarTarea();
                                System.out.println("Respuesta del servidor (tarea): " + respuestaTarea);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        hiloTarea2.start();
                        hiloTarea2.join();
                        break;

                    case 5:
                        continuar = false;
                        System.out.println("Finalizando la ejecución...");
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            }
            scanner.close(); // Cerrando el Scanner
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
