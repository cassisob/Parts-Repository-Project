package server;

import java.io.Serializable;

public class SubPart implements Serializable {
    private Part part;
    private int quantity;

    public SubPart(Part part, int quantity) {
        this.part = part;
        this.quantity = quantity;
    }

    public Part getPart() {
        return part;
    }

    public int getQuantity() {
        return quantity;
    }
}