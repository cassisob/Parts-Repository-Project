package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class PartRepositoryServer {
    public static void main(String[] args) {
        try {
            // Cria e exporta o objeto do repositório de peças
            PartRepositoryImpl repo = new PartRepositoryImpl("Main Repository");
            LocateRegistry.createRegistry(1099); // Porta padrão do RMI Registry
            Naming.rebind("rmi://localhost/PartRepository", repo);
            System.out.println("Part Repository Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
