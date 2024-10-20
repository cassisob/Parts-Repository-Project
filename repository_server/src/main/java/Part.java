import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Part extends Remote {
    int getCode() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    List<SubPart> getSubParts() throws RemoteException;
    boolean isPrimitive() throws RemoteException;

    void addSubPart(Part subPart, int quantity) throws RemoteException;
}
