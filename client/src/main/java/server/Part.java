package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interface para uma peça (Part)
public interface Part extends Remote {
    int getCode() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    List<SubPart> getSubParts() throws RemoteException;
    boolean isPrimitive() throws RemoteException;

    void addSubPart(Part subPart, int quantity) throws RemoteException;
}
