package views;

import checkInput.CheckInput;
import controller.AccountManager;
import controller.HotelManager;
import hotelService.MassageService;
import hotelService.MudBathService;
import model.Account;
import model.Invoice;
import model.Renter;
import model.Room;
import storage.TextFileFactory;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    static final AccountManager accountManager = AccountManager.getINSTANCE();
    static final HotelManager hotelManager = HotelManager.getINSTANCE();
    static TextFileFactory textFileFactory = TextFileFactory.getINSTANCE();
    static CheckInput checkInput = CheckInput.getINSTANCE();
    static  Scanner scanner = new Scanner(System.in);
    static String errorInputOption = "---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------";
    static String intError = "Mời nhập kiểu số";

    public static void main(String[] args) {
           loginMenu();








    }





     static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------Chào mừng bạn đến với khách sạn Thành Công-----------------");
        while (true){
            System.out.println("1.Đăng nhập");
            System.out.println("2.Đăng ký");
            System.out.println("3.Lấy lại mật khẩu");
            System.out.println("4.Thoát phần mềm");
            System.out.println("5.Reset toàn bộ tài khoản");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    if(!accountManager.login()){
                        System.err.println("Đăng nhập thất bại, tài khoản hoặc mật khẩu không tồn tại!!");
                    }
                    else {
                        System.err.println("Đăng nhập thành công !! mời tuỳ chọn các chức năng");
                        mainMenu();
                    }
                    break;
                case "2":
                    accountManager.createNewAccount();
                    break;
                case "3":
                    accountManager.passwordRetrieval();
                    break;
                case "4":
                    System.exit(0);
                    break;
                case "5":
                    accountManager.resetData();
                    System.err.println("---------------Đã thiết lập lại toàn bộ dữ liệu tài khoản!!---------------");
                    break;
                default:
                    System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");
                    break;
            }
        }
    }
    static void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        String choose;
        do {
            System.out.println("1.Xem thông tin tài khoản");
            System.out.println("2.Thêm phòng mới");
            System.out.println("3.Thiết lập phòng theo id");
            System.out.println("4.Xem danh sách các phòng còn trống theo giá");
            System.out.println("5.Xem danh sách tất cả các phòng");
            System.out.println("6.Cho khách thuê phòng/Tạo hoá đơn mới");
            System.out.println("7.Xem thông tin tất cả hoá đơn");
            System.out.println("8.Thiết lập hoá đơn theo id");
            System.out.println("9.Xem doanh thu");
            System.out.println("0.Thoát ra ngoài");
            System.out.println("11.Thiết lập lại toàn bộ dữ liệu quản lý");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    System.err.println(accountManager.getAccountInformation());
                    accountMenu();
                    break;
                case "2":
                    hotelManager.createNewRoom();
                    break;
                case "3":
                    roomMenu();
                    break;
                case "4":
                    hotelManager.showRoomsEmptyByPrice();
                    break;
                case "5":
                    hotelManager.showAllRoom();
                    break;
                case "6":
                    hotelManager.createNewInvoice();
                    break;
                case "7":
                    hotelManager.showAllInvoice();
                    break;
                case "8":
                    invoiceMenu();
                    break;
                case "9":
                    hotelManager.showRevenueMenu();
                    break;
                case "0":
                    break;
                case "11":
                    hotelManager.resetData();
                    break;
                default:
                    System.err.println(errorInputOption);
                    break;
            }
        }while (!choose.equals("0"));
    }
    static void accountMenu(){
        Scanner scanner = new Scanner(System.in);
        String choose;
        do {
            System.out.println("1.Thay đổi mật khẩu");
            System.out.println("2.Thay đổi email");
            System.out.println("3.Thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    accountManager.account.setAccountPassword(accountManager.enterAccountPassword());
                    saveAccount();
                    System.err.println("thiết lập thành công");
                    break;
                case "2":
                    accountManager.account.setAccountEmail(accountManager.createAccountEmail());
                    saveAccount();
                    System.err.println("thiết lập thành công");
                    break;
                case "3":
                    break;
                default:
                    System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");

            }
        }while (!choose.equals("3"));
    }
    static void settingInvoiceMenu(Invoice invoice){
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
                        hotelManager.changeRoomInInvoice(invoice);
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
    static void invoiceMenu(){
        Invoice invoice;
        if ((invoice = hotelManager.getInvoiceById()) != null) {
            String choose;
            do {
                System.out.println("1.Xem thông tin hoá đơn");
                System.out.println("2.Thanh toán hoá đơn");
                System.out.println("3.Chỉnh sửa hoá đơn");
                System.out.println("4.Thoát");
                choose= scanner.nextLine();
                switch (choose){
                    case "1":
                        hotelManager.showInvoice(invoice);
                        break;
                    case "2":
                        hotelManager.payInvoice(invoice);
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
    static void setRenterMenu(Invoice invoice){
        Renter renter;
        if((renter=hotelManager.getRenterByIndex(invoice))!=null){
            String choose;
            do {
                System.out.println("1. Thay đổi tên khách hàng");
                System.out.println("2. Thay đổi số điện thoại của khách hàng");
                System.out.println("3. Thay đổi chứng minh thư của khách hàng");
                System.out.println("4. Thoát");
                choose = scanner.nextLine();
                switch (choose){
                    case "1":
                        renter.setName(hotelManager.enterNameOfRenter());
                        break;
                    case "2":
                        renter.setPhoneNumber(hotelManager.enterNumberPhoneOfRenter());
                        break;
                    case "3":
                        renter.setIdCard(hotelManager.enterIDCardOfRenter());
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
    static void setSeviceMenu(Invoice invoice){
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
    static void removeService(Invoice invoice){
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
    static void addServiceToInvoice(Invoice invoice){
        String choose;
        do {
            System.out.println("Nhấn 1 để thêm dịch vụ massage");
            System.out.println("Nhấn 2 để thêm dịch vụ tắm bùn");
            System.out.println("Nhấn 3 để thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    hotelManager.addService(invoice, MassageService.getINSTANCE());
                    saveInvoiceList();
                    break;
                case "2":
                    hotelManager.addService(invoice, MudBathService.getINSTANCE());
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
    static void roomMenu() {
        Room room;
        if ((room = hotelManager.getRoomById()) != null) {
            String choose;
            do {
                System.out.println("1.Xem thông tin phòng");
                System.out.println("2.Thay đổi thông tin phòng");
                System.out.println("3.Xoá phòng");
                System.out.println("4.Thoát");
                choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        hotelManager.showRoom(room);
                        break;
                    case "2":
                        settingRoomMenu(room);
                        break;
                    case "3":
                        hotelManager.removeRoom(room);
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
    static void settingRoomMenu(Room room){
        String choose;
        do {
            System.out.println("1.Thay đổi id");
            System.out.println("2.Thay đổi giá");
            System.out.println("3.Thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    hotelManager.setIdRoom(room);
                    break;
                case "2":
                    hotelManager.setPriceOfRoom(room);
                    break;
                case "3":
                    break;
                default:
                    System.err.println(errorInputOption);
                    break;
            }
        }while ((!choose.equals("3")));
    }
    static void saveInvoiceList(){
        textFileFactory.saveFile(hotelManager.listInvoice,"listInvoice.txt");
    }
    static void saveListRenter(){
        textFileFactory.saveFile(hotelManager.listRenter, "listRenter.txt");
    }
    static void saveListRoom(){
        textFileFactory.saveFile(hotelManager.listRoom,"listRoom.txt");
    }
    static void saveAccount(){
        textFileFactory.saveFile(accountManager.listAccount,"account.txt");
    }
}
