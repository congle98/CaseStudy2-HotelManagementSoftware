package Views;

import Controller.AccountManager;
import Controller.HotelManager;
import Model.Account;
import Model.Renter;
import Model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
public class Client {
    static final AccountManager accountManager = AccountManager.getINSTANCE();
    static final HotelManager hotelManager = HotelManager.getINSTANCE();
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
            System.out.println("9.Xem tổng doanh thu");
            System.out.println("10.Thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    System.err.println(accountManager.getAccountInformation());
                    accountManager.accountMenu();
                    break;
                case "2":
                    hotelManager.createNewRoom();
                    break;
                case "3":
                    hotelManager.roomMenu();
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
                    hotelManager.invoiceMenu();
                    break;
                case "9":
                    hotelManager.showRevenueMenu();
            }
        }while (!choose.equals("10"));
    }
}
