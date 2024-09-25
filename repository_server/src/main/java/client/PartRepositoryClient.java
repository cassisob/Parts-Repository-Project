package client;

import server.Part;
import server.PartRepository;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class PartRepositoryClient {
    public static void main(String[] args) {
        try {
            PartRepository repo = (PartRepository) Naming.lookup("rmi://localhost/PartRepository");
            Scanner scanner = new Scanner(System.in);
            Part currentPart = null;

            while (true) {
                System.out.print("> ");
                String command = scanner.nextLine();

                switch (command) {
                    case "listp":
                        List<Part> parts = repo.listParts();
                        for (Part part : parts) {
                            System.out.println("Code: " + part.getCode() + ", Name: " + part.getName());
                        }
                        break;

                    case "getp":
                        System.out.print("Enter part code: ");
                        int code = Integer.parseInt(scanner.nextLine());
                        currentPart = repo.getPart(code);
                        if (currentPart != null) {
                            System.out.println("Part found: " + currentPart.getName());
                        } else {
                            System.out.println("Part not found");
                        }
                        break;

                    case "showp":
                        if (currentPart != null) {
                            System.out.println("Code: " + currentPart.getCode());
                            System.out.println("Name: " + currentPart.getName());
                            System.out.println("Description: " + currentPart.getDescription());
                            System.out.println("Is primitive: " + currentPart.isPrimitive());
                        } else {
                            System.out.println("No current part selected");
                        }
                        break;

                    case "addp":
                        System.out.print("Enter part name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter part description: ");
                        String description = scanner.nextLine();
                        System.out.print("Is primitive (true/false): ");
                        boolean isPrimitive = Boolean.parseBoolean(scanner.nextLine());
                        Part newPart = repo.createPart(name, description, isPrimitive);
                        System.out.println("Added part with code: " + newPart.getCode());
                        break;

                    case "quit":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Unknown command");
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
