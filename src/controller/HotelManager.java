package controller;

import checkInput.CheckInput;
import hotelService.MassageService;
import hotelService.MudBathService;
import hotelService.Service;
import model.Invoice;
import model.Renter;
import model.Room;
import storage.TextFileFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class HotelManager {
    private static  HotelManager INSTANCE;
    private Scanner scanner = new Scanner(System.in);
    private TextFileFactory textFileFactory = TextFileFactory.getINSTANCE();
    private CheckInput checkInput = CheckInput.getINSTANCE();
    private ArrayList<Room> listRoom =  textFileFactory.readerFile("listRoom.txt");
    private ArrayList<Invoice> listInvoice = textFileFactory.readerFile("listInvoice.txt");
    private ArrayList<Renter> listRenter = textFileFactory.readerFile("listRenter.txt");;
    private String successNotify = "Thiết lập thành công";
    private String errorInputOption = "---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------";
    private String intError = "Mời nhập kiểu số";
    private String dateError = "Nhập sai ngày tháng hoặc định dạng ngày tháng mời nhập lại VD:01/12/2021";
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
        Double priceOfRoom;
        String pOR;
        while (!checkInput.checkINT(pOR=scanner.nextLine())){
            System.err.println(intError);
        }
        priceOfRoom = Double.parseDouble(pOR);
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
        saveListRoom();
        System.out.println("Đã thêm phòng thành công!");
    }

    // lấy ra các phòng trống, sắp xếp và hiển thị theo giá
    private ArrayList<Room> roomsEmpty(){
        System.out.println("Mời nhập khoảng giá");
        double price;
        String pr="";
//        while (!checkInput.checkINT(pr=scanner.nextLine())){
//            System.err.println(intError);
//        }
        price = Double.parseDouble(checkInt(pr));
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
        ArrayList<Room> rooms = roomsEmpty();
        if(rooms.size()==0){
            System.err.println("xin lỗi không có phòng trống trong khoảng giá này");
        }
        else {
            for (Room r:rooms
                 ) {
                showRoom(r);
            }
        }
    }

    //hiển thị tất cả các phòng
    public void showAllRoom(){

        if(listRoom.size()==0){
            System.err.println("Chưa có phòng nào");
        }
        else {
            for (Room r:listRoom
                 ) {
                showRoom(r);
                System.out.println("--------------------------------");
            }
        }

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
                        System.err.println(errorInputOption);
                        break;
                }
            } while (!choose.equals("3")&&!choose.equals("4"));

        }
    }
    private void showRoom(Room room){
        String empty = room.isEmpty()?", Còn trống":", Đã có người thuê";
        String show =  "Phòng ID: "+room.getId()+" ,Giá: "+room.getPrice()+" vnđ"+ empty;
        if(room.isEmpty()){
            System.out.println(show);
        }
        else {
            System.err.println(show);
        }
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
            saveListRoom();
            saveInvoiceList();
        }
        else {
            System.err.println("Xin lỗi id của phòng đã tồn tại");
        }
    }

    private void removeRoom(Room room){
        listRoom.remove(room);
        System.out.println("Đã xoá phòng thành công");
        saveInvoiceList();
        saveListRoom();
    }
    private void setPriceOfRoom(Room room){
        System.out.println("Mời nhập giá mới cho phòng");
        Double priceOfRoom;
        String pOR;
        while (!checkInput.checkINT(pOR=scanner.nextLine())){
            System.err.println(intError);
        }
        priceOfRoom = Double.parseDouble(pOR);
        room.setPrice(priceOfRoom);
        saveListRoom();
        saveInvoiceList();
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
                    System.err.println(errorInputOption);
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
        int renterNumber;
        String rN;
        while (!checkInput.checkINT(rN=scanner.nextLine())){
            System.err.println(intError);
        }
        renterNumber = Integer.parseInt(rN);
        ArrayList<Renter> renters = new ArrayList<>();
        for (int i = 0; i < renterNumber; i++) {
            Renter renter = createNewRenter();
            renters.add(renter);
        }
        saveListRenter();
        invoice.setRenters(renters);
        listRenter.addAll(renters);

    }
    private void addDayStartToInvoice(Invoice invoice){
        System.err.println("Mời nhập thời gian bắt đầu cho thuê theo định dạng dd/mm/yyyy");
        while (true){
            try {
                String dayStart = scanner.nextLine();
                String time[] = dayStart.split("/");
                LocalDate dateTime = LocalDate.of(Integer.parseInt(time[2]),Integer.parseInt(time[1]),Integer.parseInt(time[0]));
                invoice.setDayStart(dateTime);
                break;
            }
           catch (Exception ex){
               System.err.println(dateError);
           }
        }
    }
//    private void addDayEndToInvoice(Invoice invoice){
//        System.err.println("Mời nhập ngày tháng năm kết thúc cho thuê theo định dạng dd/mm/yyyy");
//        String dayStart = scanner.nextLine();
//        String time[] = dayStart.split("/");
//        LocalDate dateTime = LocalDate.of(Integer.parseInt(time[2]),Integer.parseInt(time[1]),Integer.parseInt(time[0]));
//        invoice.setDayStart(dateTime);
//    }
    //tạo mới một hoá đơn
    public void createNewInvoice(){
        Invoice invoice = new Invoice();
        if(addRoomToInvoice(invoice)){
            addRenterToInvoice(invoice);
            addDayStartToInvoice(invoice);
            int id = (listInvoice.size()==0)?1:listInvoice.get(listInvoice.size()-1).getId()+1;
            invoice.setId(id);
            invoice.getRoom().setEmpty(false);
            listInvoice.add(invoice);
            saveListRoom();
            saveInvoiceList();
        }
    }

    // hiển thị tất cả hoá đơn

    public void showAllInvoice(){
        if(listInvoice.size()==0){
            System.err.println("Chưa có hoá đơn nào");
        }
        else {
            for (int i = 0; i < listInvoice.size(); i++) {
                showInvoice(listInvoice.get(i));
            }
        }
    }
    // lấy hoá đơn theo id
    public Invoice getInvoiceById(){

        System.out.println("Mời nhập id của hoá đơn");
        int idOfInvoice;
        String iOR;
        while (!checkInput.checkINT(iOR=scanner.nextLine())){
            System.err.println(intError);
        }
        idOfInvoice = Integer.parseInt(iOR);
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
                        System.err.println(errorInputOption);
                        break;
                }
            }while (!choose.equals("4"));
        }
    }
    // thiển thị hoá đơn
    private String formatDate(LocalDate localDate){
        if( localDate!=null){
            String date = String.valueOf(localDate);
            String []temp = date.split("-");
            date = temp[2]+"/"+temp[1]+"/"+temp[0];
            return date;
        }
        else {
            return "Chưa có dữ liệu";
        }
    }
    public void showInvoice(Invoice invoice) {
        String renterInfor = "";
        String serviceInfor = "";
        String price = "";
        if (invoice.getPrice() == 0) {
            price = "Chưa thanh toán";
        } else {
            price += invoice.getPrice() + "vnđ";
        }
        for (int i = 0; i < invoice.getRenters().size(); i++) {
            renterInfor += i + 1 + "." + invoice.getRenters().get(i).getName() + ", số điện thoại: " + invoice.getRenters().get(i).getPhoneNumber() + ", số CMT: " + invoice.getRenters().get(i).getIdCard() + " .\n";

        }
        for (int i = 0; i < invoice.getServices().size(); i++) {
            serviceInfor += i + 1 + "." + invoice.getServices().get(i).getDescribe() + "\n";
        }
        String checkPaid = invoice.getPaid() ? " Đã thanh toán" : " Chưa thanh toán";
        String infor = "--------------Hoá đơn số: "+invoice.getId()+" Loại: "+ checkPaid+
                "--------------\nPhòng ID: "+invoice.getRoom().getId()+
                "\nKhách: \n"+renterInfor+
                "\nDịch vụ: \n"+serviceInfor+
                "\nTổng tiền: "+price+
                "\nNgày nhận phòng: "+formatDate(invoice.getDayStart())+", Ngày trả phòng: "+ formatDate(invoice.getDayEnd())+
                "\n-------------------------------------------------";
        if(invoice.getPaid()){
            System.err.println(infor);
        }
        else {
            System.out.println(infor);
        }

    }

    //nhập ngày kết thúc hoá đơn
    private void addDayEndToInvoice(Invoice invoice){
        do {
            System.err.println("Mời nhập ngày kết thúc hoá đơn theo định dạng dd/mm/yyyy");
            String dayEnd;
            String time[];
            LocalDate dateTime;
//            while (!checkInput.checkDate(dayEnd=scanner.nextLine())){
//                System.err.println(dateError);
//            }
//            String time[] = dayEnd.split("/");
//            LocalDate dateTime = LocalDate.of(Integer.parseInt(time[2]),Integer.parseInt(time[1]),Integer.parseInt(time[0]));
            while (true){
                try {
                    dayEnd = scanner.nextLine();
                    time = dayEnd.split("/");
                    dateTime = LocalDate.of(Integer.parseInt(time[2]),Integer.parseInt(time[1]),Integer.parseInt(time[0]));
                    break;
                }
                catch (Exception ex){
                    System.err.println(dateError);
                }
            }
            if(DAYS.between(invoice.getDayStart(), dateTime)>0){
                invoice.setDayEnd(dateTime);
                break;
            }
            else {
                System.err.println("Ngày kết thúc hoá đơn phải lớn hơn ngày bắt đầu !!!");
            }
        }while (true);
    }
    //
    private void setPriceToInvoice(Invoice invoice){
        long dayToRent = DAYS.between(invoice.getDayStart(),invoice.getDayEnd());
        double priceService = 0;
        for (int i = 0; i < invoice.getServices().size(); i++) {
            priceService+=invoice.getServices().get(i).getPrice();
        }
        double price = dayToRent*invoice.getRoom().getPrice()+priceService;
        invoice.setPrice(price);
//        revenue+=price;
    }

    //thanh toán hoá đơn
    public void payInvoice(Invoice invoice){
        if (!invoice.getPaid()){
            addDayEndToInvoice(invoice);
            setPriceToInvoice(invoice);
            invoice.setPaid(true);
            invoice.getRoom().setEmpty(true);
            System.err.println("Hoá đơn ID:"+invoice.getId()+ " đã được thanh toán, số tiền là " +invoice.getPrice()+"vnđ , cảm ơn quý khách!");
            for (Room r:listRoom
                 ) {
                if(r.getId().equals(invoice.getRoom().getId())){
                    r.setEmpty(true);
                }
            }
            saveInvoiceList();
            saveListRoom();
        }
        else {
            System.err.println("xin lỗi hoá đơn này đã được thanh toán không thể thanh toán lại ");
        }
    }
    public void settingInvoiceMenu(Invoice invoice){
        String choose;
        if(!invoice.getPaid()){
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
                        System.err.println(errorInputOption);
                        break;
                }
            }while (!choose.equals("4"));
        }
        else {
            System.err.println("Xin lỗi không thể sửa hoá đơn đã thanh toán");
        }
    }
    private void changeRoomInInvoice(Invoice invoice){
        System.out.println("Mời nhập id phòng muốn chuyển");
        String idOfRoom = scanner.nextLine();
        boolean check = false;
        for (Room room: listRoom
             ) {
            if(room.getId().equals(idOfRoom)&&room.isEmpty()){
                invoice.getRoom().setEmpty(true);
                for (Room r:listRoom
                     ) {
                    if(r.getId().equals(invoice.getRoom().getId())){
                        r.setEmpty(true);
                    }
                }
                invoice.setRoom(room);
                invoice.getRoom().setEmpty(false);
                for (Room r:listRoom
                ) {
                    if(r.getId().equals(invoice.getRoom().getId())){
                        r.setEmpty(false);
                    }
                }
                check = true;
                saveInvoiceList();
                saveListRoom();
                System.out.println(successNotify);
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
                        System.err.println(errorInputOption);
                        break;
                }
                saveInvoiceList();
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
        System.err.println(successNotify);
        saveListRenter();
        saveInvoiceList();
    }
    private void setPhoneOfRenter(Renter renter){
        System.out.println("Mời nhập số điện thoại mới");
        String numberOfRenter = scanner.nextLine();
        renter.setPhoneNumber(numberOfRenter);
        System.err.println(successNotify);
        saveListRenter();
        saveInvoiceList();
    }
    private void setIdCardOfRenter(Renter renter){
        System.out.println("Mời nhập chứng minh thư mới");
        String idCardOfRenter = scanner.nextLine();
        renter.setIdCard(idCardOfRenter);
        System.err.println(successNotify);
        saveListRenter();
        saveInvoiceList();
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
                    System.err.println(errorInputOption);
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
                    saveInvoiceList();
                    break;
                case "2":
                    addService(invoice,MudBathService.getINSTANCE());
                    saveInvoiceList();
                    break;
                case "3":
                    break;
                default:
                    System.err.println(errorInputOption);
                    break;
            }
        }while (!choose.equals("3"));
    }
    private void removeService(Invoice invoice){
        if(invoice.getServices().size()!=0){
            System.out.println("Bạn muốn xoá dịch vụ thứ mấy");
            int index;
            String idex;
            while (!checkInput.checkINT(idex=scanner.nextLine())){
                System.err.println(intError);
            }
            index = Integer.parseInt(idex);
            if(index<=invoice.getServices().size()){
                invoice.getServices().remove(index-1);
                System.err.println("Đã xoá dịch vụ thành công");
                saveInvoiceList();
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
        System.err.println(successNotify);
    }

    public void showRevenueMenu(){
        Double totalRevenue = 0.0;
        Double []revenue = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        for (Invoice invoice:listInvoice
        ) {
            totalRevenue+=invoice.getPrice();
            if(invoice.getDayEnd()!=null){
                switch (invoice.getDayEnd().getMonthValue()){
                    case 1:
                        revenue[0]+=invoice.getPrice();
                        break;
                    case 2:
                        revenue[1]+=invoice.getPrice();
                        break;
                    case 3:
                        revenue[2]+=invoice.getPrice();
                        break;
                    case 4:
                        revenue[3]+=invoice.getPrice();
                        break;
                    case 5:
                        revenue[4]+=invoice.getPrice();
                        break;
                    case 6:
                        revenue[5]+=invoice.getPrice();
                        break;
                    case 7:
                        revenue[6]+=invoice.getPrice();
                        break;
                    case 8:
                        revenue[7]+=invoice.getPrice();
                        break;
                    case 9:
                        revenue[8]+=invoice.getPrice();
                        break;
                    case 10:
                        revenue[9]+=invoice.getPrice();
                        break;
                    case 11:
                        revenue[10]+=invoice.getPrice();
                        break;
                    case 12:
                        revenue[11]+=invoice.getPrice();
                        break;
                }
            }
        }
        System.err.println("-----------------BẢNG DOANH THU THEO THÁNG-------------------");
        for (int i = 1; i <13 ; i++) {
            System.out.println("Tháng "+i+" doanh thu là: "+revenue[i-1]+"vnđ");
        }
        System.out.println("-------------Tổng doanh thu: "+totalRevenue+"vnđ-------------");
    }
    private void saveInvoiceList(){
       textFileFactory.saveFile(listInvoice,"listInvoice.txt");
    }
    private void saveListRenter(){
        textFileFactory.saveFile(listRenter,"listRenter.txt");
    }
    private void saveListRoom(){
        textFileFactory.saveFile(listRoom,"listRoom.txt");
    }
    public void resetData(){
        textFileFactory.resetFile("listInvoice.txt");
        textFileFactory.resetFile("listRenter.txt");
        textFileFactory.resetFile("listRoom.txt");
        listInvoice = new ArrayList<>();
        listRoom = new ArrayList<>();
        listRenter= new ArrayList<>();
        System.out.println(successNotify);

    }
    private String checkInt(String str){
        while (!checkInput.checkINT(str=scanner.nextLine())){
            System.err.println(intError);
        }
        return str;
    }



}
