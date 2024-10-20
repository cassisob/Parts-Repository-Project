package console.client;

import server.Part;
import server.PartRepository;
import server.SubPart;

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

                            if (!currentPart.isPrimitive()) {
                                List<SubPart> subParts = currentPart.getSubParts();
                                if (subParts.isEmpty()) {
                                    System.out.println("No subparts.");
                                } else {
                                    System.out.println("Subparts:");
                                    for (SubPart subPart : subParts) {
                                        System.out.println("  SubPart Code: " + subPart.getPart().getCode() +
                                                ", Name: " + subPart.getPart().getName() +
                                                ", Quantity: " + subPart.getQuantity());
                                    }
                                }
                            }
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

                    case "addsubpart":
                        if (currentPart == null) {
                            System.out.println("No current part selected");
                        } else if (currentPart.isPrimitive()) {
                            System.out.println("Cannot add subparts to a primitive part");
                        } else {
                            System.out.print("Enter subpart code: ");
                            int subPartCode = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter quantity: ");
                            int quantity = Integer.parseInt(scanner.nextLine());

                            Part subPart = repo.getPart(subPartCode);
                            if (subPart == null) {
                                System.out.println("Subpart not found");
                            } else {
                                currentPart.addSubPart(subPart, quantity);
                                System.out.println("Added subpart with code: " + subPartCode);
                            }
                        }
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
