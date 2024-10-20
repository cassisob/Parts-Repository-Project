package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote {
    String getName() throws RemoteException;
    int getPartCount() throws RemoteException;
    List<Part> listParts() throws RemoteException;
    Part getPart(int code) throws RemoteException;
    void addPart(Part part) throws RemoteException;

    Part createPart(String name, String description, boolean isPrimitive) throws RemoteException;

    void addSubPartToPart(int partCode, int subPartCode, int quantity) throws RemoteException;

}