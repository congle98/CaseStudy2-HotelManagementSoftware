package model;

import java.io.Serializable;

public class Room implements Serializable {
    private String id;
    private Double price;
    private boolean empty;

    public Room() {
        this.empty = true;
    }

    public Room(String id, Double price, boolean empty) {
        this.id = id;
        this.price = price;
        this.empty = empty;
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

//    @Override
//    public String toString() {
//        String empty = isEmpty()?", Phòng còn trống":", Phòng đã có người thuê";
//        return "Phòng" +
//                " id: " + id +
//                ", giá: " + price +"vnđ"+
//                empty+"\n";
//    }
}
