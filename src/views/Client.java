package views;

import controller.CheckInput;
import controller.AccountManager;
import controller.HotelManager;
import model.MassageService;
import model.MudBathService;
import model.Invoice;
import model.Renter;
import model.Room;

import java.util.Scanner;

public class Client {

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
                    accountManager.getAccount().setAccountPassword(accountManager.createAccountPassword());
                    accountManager.saveAccount();
                    break;
                case "2":
                    accountManager.getAccount().setAccountEmail(accountManager.createAccountEmail());
                    accountManager.saveAccount();
                    break;
                case "3":
                    break;
                default:
                    System.err.println(errorInputOption);

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
                System.out.println("4.Thoát ra ngoài");
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
                System.out.println("4.Thoát ra ngoài");
                choose= scanner.nextLine();
                switch (choose){
                    case "1":
                        if(!invoice.getPaid()){
                            System.out.println(hotelManager.showInvoice(invoice));
                        }
                        else {
                            System.err.println(hotelManager.showInvoice(invoice));
                        }
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
                System.out.println("1.Thay đổi tên khách hàng");
                System.out.println("2.Thay đổi số điện thoại của khách hàng");
                System.out.println("3.Thay đổi chứng minh thư của khách hàng");
                System.out.println("4.Thoát ra ngoài");
                choose = scanner.nextLine();
                switch (choose){
                    case "1":
                        renter.setName(hotelManager.createNameOfRenter());
                        break;
                    case "2":
                        renter.setPhoneNumber(hotelManager.createNumberPhoneOfRenter());
                        break;
                    case "3":
                        renter.setIdCard(hotelManager.createIDCardOfRenter());
                        break;
                    case "4":
                        break;
                    default:
                        System.err.println(errorInputOption);
                        break;
                }
                hotelManager.saveInvoiceList();
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
                hotelManager.saveInvoiceList();
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
            System.out.println("1.Thêm dịch vụ massage");
            System.out.println("2.Thêm dịch vụ tắm bùn");
            System.out.println("3.Thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    hotelManager.addService(invoice, MassageService.getINSTANCE());
                    break;
                case "2":
                    hotelManager.addService(invoice, MudBathService.getINSTANCE());
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
                System.out.println("4.Thoát ra ngoài");
                choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        hotelManager.showRoomInformation(room);
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
    static final AccountManager accountManager = AccountManager.getINSTANCE();
    static final HotelManager hotelManager = HotelManager.getINSTANCE();
    static CheckInput checkInput = CheckInput.getINSTANCE();
    static Scanner scanner = new Scanner(System.in);
    static String errorInputOption = "---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------";
    static String intError = "Mời nhập kiểu số";
}
