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
//    private ArrayList<Service> listService = new ArrayList<>();



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

    //lấy ra room theo id để chỉnh sửa
    public Room getRoomById(){
        System.out.println("Mời nhập id của phòng");
        String idOfRoom = scanner.nextLine();

        for (Room room: listRoom
        ) {
            if(room.getId().equals(idOfRoom)){
                return room;
            }
        }
        System.err.println("Xin lỗi phòng không tồn tại hoặc sai id");
        return null;
    }


    //menuroom
    public void roomMenu() {
        Room room;
        if ((room = getRoomById()) != null) {
            String choose;
            do {
                System.out.println("1.Xem thông tin phòng");
                System.out.println("2.Thay đổi thông tin phòng");
                System.out.println("3.Xoá phòng");
                System.out.println("4.Thoát");
                choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        showRoom(room);
                        break;
                    case "2":
                        settingRoomMenu(room);
                        break;
                    case "3":
                        removeRoom(room);
                        break;
                    case "4":
                        break;
                    default:
                        System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");
                        break;
                }
            } while (!choose.equals("3")&&!choose.equals("4"));

        }
    }
    private void showRoom(Room room){
        System.out.println(room);
    }
    private void setIdRoom(Room room){
        System.out.println("Mời nhập id mới của phòng");
        String idOfRoom = scanner.nextLine();
        boolean check = true;
        for (Room r: listRoom
        ) {
            if(r.getId().equals(idOfRoom)){
                check = false;
            }
        }
        if(check){
            room.setId(idOfRoom);
        }
        else {
            System.err.println("Xin lỗi id của phòng đã tồn tại");
        }
    }

    private void removeRoom(Room room){
        listRoom.remove(room);
        System.out.println("Đã xoá phòng thành công");
    }
    private void setPriceOfRoom(Room room){
        System.out.println("Mời nhập giá mới cho phòng");
        Double priceOfRoom = Double.parseDouble(scanner.nextLine());
        room.setPrice(priceOfRoom);
    }
    private void settingRoomMenu(Room room){
        String choose;
        do {
            System.out.println("1.Thay đổi id");
            System.out.println("2.Thay đổi giá");
            System.out.println("3.Thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    setIdRoom(room);
                    break;
                case "2":
                    setPriceOfRoom(room);
                    break;
                case "3":
                    break;
                default:
                    System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");
                    break;
            }
        }while ((!choose.equals("3")));
    }


    //thêm phòng vào hoá đơn
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
    //thêm khách thuê vào hoá đơn
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
    //tạo mới một hoá đơn
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
        if(listRoom.size()==0){
            System.err.println("Chưa có hoá đơn nào");
        }
        else {
            for (Invoice invoice:listInvoice
            ) {
                System.out.println(invoice.getInformation());
            }
        }
    }
    // lấy hoá đơn theo id
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


    //menu hoá đơn
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
                        settingInvoiceMenu(invoice);
                        break;
                    case "4":
                        break;
                    default:
                        System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");
                        break;
                }
            }while (!choose.equals("4")&&!choose.equals("2"));
        }
    }
    // thiển thị hoá đơn
    public void showInvoice(Invoice invoice){
        System.out.println(invoice.getInformation());
    }
    //thanh toán hoá đơn
    public void payInvoice(Invoice invoice){
        if (!invoice.getPaid()){
            invoice.setPaid(true);
            invoice.getRoom().setEmpty(true);
            System.out.println("hoá đơn đã được thanh toán, cảm ơn quý khách");
        }
        else {
            System.err.println("xin lỗi hoá đơn này đã được thanh toán không thể thanh toán lại ");
        }
    }
    public void settingInvoiceMenu(Invoice invoice){
        String choose;
        do {
            System.out.println("1.Thay đổi phòng");
            System.out.println("2.Thay đổi thông tin khách hàng");
            System.out.println("3.Quản lý dịch vụ");
            System.out.println("4.Thoát");
            choose= scanner.nextLine();
            switch (choose){
                case "1":
                    changeRoomInInvoice(invoice);
                    break;
                case "2":
                    setRenterMenu(invoice);
                    break;
                case "3":
                    setSeviceMenu(invoice);
                    break;
                case "4":
                    break;
                default:
                    System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");
                    break;
            }
        }while (!choose.equals("4"));
    }
    private void changeRoomInInvoice(Invoice invoice){
        System.out.println("Mời nhập id phòng muốn chuyển");
        String idOfRoom = scanner.nextLine();
        boolean check = false;
        for (Room room: listRoom
             ) {
            if(room.getId().equals(idOfRoom)&&room.isEmpty()){
                invoice.getRoom().setEmpty(true);
                invoice.setRoom(room);
                invoice.getRoom().setEmpty(false);
                check = true;
            }
        }
        if(!check){
            System.err.println("Xin lỗi quý khách phòng không tồn tại hoặc đã có người thuê");
        }
    }

    private void setRenterMenu(Invoice invoice){
        Renter renter;
        if((renter=getRenterByIndex(invoice))!=null){
            String choose;
            do {
                System.out.println("1. Thay đổi tên khách hàng");
                System.out.println("2. Thay đổi số điện thoại khách hàng");
                System.out.println("3. Thay đổi email khách hàng");
                System.out.println("4. Thoát");
                choose = scanner.nextLine();
                switch (choose){
                    case "1":
                        setNameOfRenter(renter);
                        break;
                    case "2":
                        setPhoneOfRenter(renter);
                        break;
                    case "3":
                        setIdCardOfRenter(renter);
                        break;
                    case "4":
                        break;
                    default:
                        System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");
                        break;
                }
            }
            while (!choose.equals("4"));
        }
    }
    private Renter getRenterByIndex(Invoice invoice){
        System.out.println("Mời vị trí của khác hàng cần thay đổi thông tin");
        int index = Integer.parseInt(scanner.nextLine());
        if(index<=invoice.getRenters().size()){
            return invoice.getRenters().get(index-1);
        }
        else
        {
            System.err.println("Bạn đã nhập sai vị trí");
            return null;
        }
    }
    private void setNameOfRenter(Renter renter){
        System.out.println("Mời nhập tên mới");
        String nameOfRenter = scanner.nextLine();
        renter.setName(nameOfRenter);
    }
    private void setPhoneOfRenter(Renter renter){
        System.out.println("Mời nhập số điện thoại mới");
        String numberOfRenter = scanner.nextLine();
        renter.setPhoneNumber(numberOfRenter);
    }
    private void setIdCardOfRenter(Renter renter){
        System.out.println("Mời nhập email mới");
        String idCardOfRenter = scanner.nextLine();
        renter.setIdCard(idCardOfRenter);
    }


    private void setSeviceMenu(Invoice invoice){
        String choose;
        do {
            System.out.println("1.Thêm dịch vụ");
            System.out.println("2.Xoá dịch vụ");
            System.out.println("3.Thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    addServiceToInvoice(invoice);
                    break;
                case "2":
                    removeService(invoice);
                    break;
                case "3":
                    break;
                default:
                    System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");
                    break;
            }
        }while (!choose.equals("3"));
    }
    private void addServiceToInvoice(Invoice invoice){
        String choose;
        do {
            System.out.println("Nhấn 1 để thêm dịch vụ massage");
            System.out.println("Nhấn 2 để thêm dịch vụ tắm bùn");
            System.out.println("Nhấn 3 để thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    addService(invoice,MassageService.getINSTANCE());
                    break;
                case "2":
                    addService(invoice,MudBathService.getINSTANCE());
                    break;
                case "3":
                    break;
                default:
                    System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");
                    break;
            }
        }while (!choose.equals("3"));
    }
    private void removeService(Invoice invoice){
        if(invoice.getServices().size()!=0){
            System.out.println("Bạn muốn xoá dịch vụ thứ mấy");
            int index = Integer.parseInt(scanner.nextLine());
            if(index<=invoice.getServices().size()){
                invoice.getServices().remove(index-1);
                System.out.println("Đã xoá dịch vụ thành công");
            }
            else {
                System.err.println("Nhập sai vị trí dịch vụ");
            }

        }
        else {
            System.err.println("Không thể xoá vì chưa có dịch vụ nào");
        }

    }
    private void addService(Invoice invoice, Service service){
        ArrayList<Service> services = invoice.getServices();
        services.add(service);
    }

}
