package Controller;

import HotelService.MassageService;
import HotelService.MudBathService;
import HotelService.Service;
import Model.Account;
import Model.Invoice;
import Model.Renter;
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
    private ArrayList<Renter> listRenter = new ArrayList<>();
    private ArrayList<Service> listService = new ArrayList<>();



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


//    private boolean checkRoomExist(String id){
//        for (Room r: listRoom
//        ) {
//            if(r.getId().equals(id)){
//                return true;
//            }
//        }
//        return false;
//    }
//    private boolean checkRoomEmpty(String id){
//        for (Room r: listRoom
//             ) {
//            if(r.isEmpty()){
//                return true;
//            }
//        }
//        return false;
//    }
    private boolean addRoomToInvoice(Invoice invoice){
        System.out.println("Mời nhập id phòng");
        Boolean check = false;
            String idRoom = scanner.nextLine();
            for (int i = 0; i < listRoom.size(); i++) {
                if(listRoom.get(i).getId().equals(idRoom)&&listRoom.get(i).isEmpty()){
                    invoice.setRoom(listRoom.get(i));
                    check = true;
                }
            }
            if(!check){
                System.err.println("Xin lỗi phòng không tồn tại hoặc đã có khách thuê");
            }
            return check;
    }

    // thiết lập tạo khách hàng
    private String enterNameOfRenter(){
        System.out.println("Mời nhập tên khách hàng");
        String nameOfRenter = scanner.nextLine();
        return nameOfRenter;
    }
    private String enterIDCardOfRenter(){
        System.out.println("Mời nhập số chứng minh thư của khách hàng");
        String IDCardOfRenter = scanner.nextLine();
        return IDCardOfRenter;
    }
    private String enterNumberPhoneOfRenter(){
        System.out.println("Mời nhập số điện thoại của khách hàng");
        String numberPhoneOfRenter = scanner.nextLine();
        return numberPhoneOfRenter;
    }
    private Renter createNewRenter(){
        Renter renter = new Renter();
        renter.setName(enterNameOfRenter());
        renter.setIdCard(enterIDCardOfRenter());
        renter.setPhoneNumber(enterNumberPhoneOfRenter());
        return renter;
    }
    private void addRenterToInvoice(Invoice invoice){
        System.out.println("Mời nhập số lượng khách thuê");
        int renterNumber = Integer.parseInt(scanner.nextLine());
        ArrayList<Renter> renters = new ArrayList<>();
        for (int i = 0; i < renterNumber; i++) {
            Renter renter = createNewRenter();
            renters.add(renter);
        }
        invoice.setRenters(renters);
        listRenter.addAll(renters);

    }
    public void createNewInvoice(){
        Invoice invoice = new Invoice();
        if(addRoomToInvoice(invoice)){
            addRenterToInvoice(invoice);
            int id = (listInvoice.size()==0)?1:listInvoice.get(listInvoice.size()-1).getId()+1;
            invoice.setId(id);
            invoice.getRoom().setEmpty(false);
            listInvoice.add(invoice);
        }
    }

    // hiển thị tất cả hoá đơn
    public void showAllInvoice(){
        for (Invoice invoice:listInvoice
             ) {
            System.out.println(invoice.getInformation());
        }
    }
    //menu hoá đơn
    public Invoice getInvoiceById(){

        System.out.println("Mời nhập id của hoá đơn");
        int idOfInvoice = Integer.parseInt(scanner.nextLine());

        for (Invoice invoice: listInvoice
             ) {
            if(invoice.getId()==idOfInvoice){
                return invoice;
            }
        }
        System.err.println("Xin lỗi hoá đơn không tồn tại");
        return null;
    }

    public void showInvoice(Invoice invoice){
        System.out.println(invoice.getInformation());
    }
    public void payInvoice(Invoice invoice){
        System.out.println(invoice.getRoom().getPrice());
        invoice.setPaid(true);
        invoice.getRoom().setEmpty(true);
    }
    public void invoiceMenu(){
        Invoice invoice;
        if ((invoice = getInvoiceById()) != null) {
            String choose;
            do {
                System.out.println("1.Xem thông tin hoá đơn");
                System.out.println("2.Thanh toán hoá đơn");
                System.out.println("3.Chỉnh sửa hoá đơn");
                System.out.println("4.Thoát");
                choose= scanner.nextLine();
                switch (choose){
                    case "1":
                        showInvoice(invoice);
                        break;
                    case "2":
                        payInvoice(invoice);
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    default:
                        System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");
                        break;
                }
            }while (!choose.equals("4"));

        }
        else {
            System.err.println("Hoá đơn không tồn tại");
        }
    }

}
