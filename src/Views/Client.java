package Views;

import Controller.AccountManager;
import Controller.HotelManager;
import Model.Account;
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
        System.out.println("Chào mừng bạn đến với khách sạn Thành Công");
        while (true){
            System.out.println("1.Đăng nhập");
            System.out.println("2.Đăng ký");
            System.out.println("3.Thoát phần mềm");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    if(!accountManager.login()){
                        System.err.println("Đăng nhập thất bại, tài khoản hoặc mật khẩu không tồn tại!!");
                    }
                    else {
                        mainMenu();
                    }
                    break;
                case "2":
                    accountManager.createNewAccount();
                    break;
                case "3":
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
            System.out.println("3.Xem danh sách các phòng còn trống theo giá");
            System.out.println("4.Xem danh sách tất cả các phòng");
            System.out.println("5.Cho khách thuê phòng/Tạo hoá đơn mới");
            System.out.println("6.Xem thông tin hoá đơn");
            System.out.println("7.Thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    System.out.println(accountManager.getAccountInformation());
                    accountMenu();
                    break;
                case "2":
                    hotelManager.createNewRoom();
                    break;
                case "3":
                    hotelManager.showRoomsEmptyByPrice();
                    break;
                case "4":
                    hotelManager.showAllRoom();
                    break;
            }
        }while (!choose.equals("7"));
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
                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
                    System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");

            }
        }while (!choose.equals("3"));
    }
}
