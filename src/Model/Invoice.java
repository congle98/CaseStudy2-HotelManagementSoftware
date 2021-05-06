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
    private ArrayList<Service> services = new ArrayList<>();
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
    public String getInformation(){
        String renterInfor = "";
        String serviceInfor = "";
        for (int i = 0; i < renters.size(); i++) {
//            if(i<renters.size()-1){
                renterInfor+=i+1+"."+renters.get(i).getName()+", số điện thoại: "+renters.get(i).getPhoneNumber()+", số CMT: "+ renters.get(i).getIdCard()+" .\n" ;
//            }
//            else if(i==renters.size()-1){
//                renterInfor+=renters.get(i).getName();
//            }
        }
        for (int i = 0; i < services.size(); i++) {
            serviceInfor+=i+1+"."+services.get(i).getDescribe()+"\n";
        }
        String checkPaid = paid?" Đã thanh toán":" Chưa thanh toán";
        return "Hoá đơn số: "+id+" Loại: "+ checkPaid+
                "\nPhòng ID: "+room.getId()+
                "\nKhách: \n"+renterInfor+
                "Dịch vụ: \n"+serviceInfor;

    }
}
