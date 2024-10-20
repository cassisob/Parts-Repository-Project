import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubPart extends Remote {
    Part getPart() throws RemoteException;
    int getQuantity() throws RemoteException;
}
