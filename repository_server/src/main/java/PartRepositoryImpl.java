import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {
    private String name;
    private Map<Integer, Part> parts;
    private int nextCode;

    protected PartRepositoryImpl(String name) throws RemoteException {
        this.name = name;
        this.parts = new HashMap<>();
        this.nextCode = 1;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public int getPartCount() throws RemoteException {
        return parts.size();
    }

    @Override
    public List<Part> listParts() throws RemoteException {
        return new ArrayList<>(parts.values());
    }

    @Override
    public Part getPart(int code) throws RemoteException {
        return parts.get(code);
    }

    @Override
    public void addPart(Part part) throws RemoteException {
        parts.put(part.getCode(), part);
    }

    @Override
    public Part createPart(String name, String description, boolean isPrimitive) throws RemoteException {
        Part part = new PartImpl(nextCode++, name, description, isPrimitive);
        addPart(part);
        return part;
    }

    @Override
    public void addSubPartToPart(int partCode, int subPartCode, int quantity) throws RemoteException {
        Part part = getPart(partCode);
        Part subPart = getPart(subPartCode);

        if (part == null || subPart == null) {
            throw new RemoteException("Part or subpart not found.");
        }

        part.addSubPart(subPart, quantity);
    }

}
