package model;

import hotelService.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice  implements Serializable {
    private int id ;
    private Room room;
    private ArrayList<Renter> renters;
    private LocalDate DayStart;
    private LocalDate DayEnd;
    private ArrayList<Service> services = new ArrayList<>();
    private double price;
    private Boolean paid;


    public Invoice() {
        paid=false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<Renter> getRenters() {
        return renters;
    }

    public void setRenters(ArrayList<Renter> renters) {
        this.renters = renters;
    }

    public LocalDate getDayStart() {
        return DayStart;
    }

    public void setDayStart(LocalDate dayStart) {
        DayStart = dayStart;
    }

    public LocalDate getDayEnd() {
        return DayEnd;
    }

    public void setDayEnd(LocalDate dayEnd) {
        DayEnd = dayEnd;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

}
