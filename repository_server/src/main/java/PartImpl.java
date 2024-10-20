import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PartImpl extends UnicastRemoteObject implements Part {
    private int code;
    private String name;
    private String description;
    private List<SubPart> subParts;
    private boolean isPrimitive;

    protected PartImpl(int code, String name, String description, boolean isPrimitive) throws RemoteException {
        this.code = code;
        this.name = name;
        this.description = description;
        this.subParts = new ArrayList<>();
        this.isPrimitive = isPrimitive;
    }

    @Override
    public int getCode() throws RemoteException {
        return code;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getDescription() throws RemoteException {
        return description;
    }

    @Override
    public List<SubPart> getSubParts() throws RemoteException {
        return subParts;
    }

    @Override
    public boolean isPrimitive() throws RemoteException {
        return isPrimitive;
    }

    @Override
    public void addSubPart(Part part, int quantity) throws RemoteException {
        SubPart subPart = new SubPartImpl(part, quantity);
        subParts.add(subPart);
    }
}
