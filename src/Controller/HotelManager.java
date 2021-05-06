package Controller;

import HotelService.MassageService;
import HotelService.MudBathService;
import HotelService.Service;
import Model.Account;
import Model.Invoice;
import Model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HotelManager {
    private static  HotelManager INSTANCE;
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Room> listRoom = new ArrayList<>();
    private ArrayList<Invoice> listInvoice = new ArrayList<>();



    private HotelManager(){

    }
    // khởi tạo với singletonParttens
    public static HotelManager getINSTANCE(){
        if(INSTANCE==null) INSTANCE = new HotelManager();
        return INSTANCE;
    }

    // khởi tạo phòng mới với các phương thức hỗ trợ nhập
    public String enterIdRoom(){
        String idRoom="";
        Boolean check = true;
        System.out.println("Mời nhập id của phòng");
        do {
            check=true;
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
        sortByPrice(listRoom);
        System.out.println("Đã thêm phòng thành công!");
    }

    // lấy ra các phòng trống, sắp xếp và hiển thị theo giá
    private ArrayList<Room> roomsEmpty(){
        System.out.println("Mời nhập khoảng giá");
        double price = Double.parseDouble(scanner.nextLine());
        ArrayList<Room> roomsEmpty = new ArrayList<>();
        for (Room room: listRoom
             ) {
            if(room.isEmpty()&&room.getPrice()<=price){
                roomsEmpty.add(room);
            }
        }
//        sortByPrice(roomsEmpty);
        return roomsEmpty;
    }
    public void showRoomsEmptyByPrice(){
        System.out.println(roomsEmpty());
    }

    //hiển thị tất cả các phòng
    public void showAllRoom(){

        System.out.println(listRoom);
    }

    //sắp xếp theo giá
    private void sortByPrice(ArrayList<Room> rooms){
        Collections.sort(rooms,new Comparator<Room>(){
            public int compare(Room o1, Room o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        });
    }
    // sắp xếp theo phòng còn trống
    private void sortByEmpty(){
        Collections.sort(listRoom,new Comparator<Room>(){
            public int compare(Room o1, Room o2) {
                return (!o1.isEmpty()&& o2.isEmpty()) ? 1 : -1;
            }
        });
    }
    // thiết lập invoice

}
