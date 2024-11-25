import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Semaphore;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            Semaphore semaforo = new Semaphore(1); // Semáforo binario para exclusión mutua
            ServicioRemotoImpl servicio = new ServicioRemotoImpl(semaforo);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ServicioRemoto", servicio);
            System.out.println("Servidor RMI listo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}