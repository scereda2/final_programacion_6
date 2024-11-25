import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioRemoto extends Remote {
    String enviarMensaje(String mensaje) throws RemoteException;

    String ejecutarTarea() throws RemoteException;
}
