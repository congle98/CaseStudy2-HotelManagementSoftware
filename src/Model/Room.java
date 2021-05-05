package Model;

public class Room {
    private String id;
    private Double price;
    private boolean empty;

    public Room() {
        this.empty = true;
    }

    public Room(String id, Double price) {
        this.id = id;
        this.price = price;
        this.empty = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
