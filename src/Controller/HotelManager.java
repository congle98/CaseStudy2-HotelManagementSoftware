package Controller;

import Model.Account;
import Model.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class HotelManager {
    public Scanner scanner = new Scanner(System.in);
    public ArrayList<Room> listRoom = new ArrayList<>();
    private static  HotelManager INSTANCE;

    private HotelManager(){

    }
    public static HotelManager getINSTANCE(){
        if(INSTANCE==null) INSTANCE = new HotelManager();
        return INSTANCE;
    }



    public String enterIdRoom(){
        String idRoom="";
        Boolean check = true;
        do {
            check=true;
            System.out.println("Mời nhập id của phòng");
            idRoom = scanner.nextLine();
            for (Room room:listRoom
                 ) {
                if(room.getId().equals(idRoom)){
                    check = false;
                    System.err.println("phòng đã tồn tại mời nhập lại");
                    break;
                }
            }

        }while (!check);
        return idRoom;
    }
    private Double enterPriceOfRoom(){
        System.out.println("Mời nhập giá phòng");
        Double priceOfRoom = Double.parseDouble(scanner.nextLine());
        return priceOfRoom;
    }
    public void createNewRoom(){
        Room room = new Room();
        String idRoom = enterIdRoom();
        Double priceOfRoom = enterPriceOfRoom();
        room.setId(idRoom);
        room.setPrice(priceOfRoom);
        listRoom.add(room);
        System.out.println("Đã thêm phòng thành công!");
    }



}
