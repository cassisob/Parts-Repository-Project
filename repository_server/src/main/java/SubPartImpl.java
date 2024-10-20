import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SubPartImpl extends UnicastRemoteObject implements SubPart {
    private Part part;
    private int quantity;

    public SubPartImpl(Part part, int quantity) throws RemoteException {
        this.part = part;
        this.quantity = quantity;
    }

    @Override
    public Part getPart() throws RemoteException {
        return part;
    }

    @Override
    public int getQuantity() throws RemoteException {
        return quantity;
    }
}
