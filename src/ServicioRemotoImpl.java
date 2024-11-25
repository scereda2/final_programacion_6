import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

public class ServicioRemotoImpl extends UnicastRemoteObject implements ServicioRemoto {
    private Semaphore semaforo;

    protected ServicioRemotoImpl(Semaphore semaforo) throws RemoteException {
        this.semaforo = semaforo;
    }

    @Override
    public String enviarMensaje(String mensaje) throws RemoteException {
        try {
            semaforo.acquire();
            System.out.println("Mensaje recibido: " + mensaje);
            Thread.sleep(2000); // Simular operación crítica
            return "Mensaje procesado: " + mensaje;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Error al procesar el mensaje.";
        } finally {
            semaforo.release();
        }
    }

    @Override
    public String ejecutarTarea() throws RemoteException {
        try {
            semaforo.acquire();
            System.out.println("Ejecutando tarea...");
            Thread.sleep(5000); // Simular una tarea que toma tiempo
            return "Tarea completada";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Error al ejecutar la tarea.";
        } finally {
            semaforo.release();
        }
    }
}
