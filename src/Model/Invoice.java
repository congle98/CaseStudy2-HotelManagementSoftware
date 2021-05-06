package Model;

import HotelService.Service;

import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice {
    private int id ;
    private Room room;
    private ArrayList<Renter> renters;
    private LocalDate DayStart;
    private LocalDate DayEnd;
    private ArrayList<Service> services;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
