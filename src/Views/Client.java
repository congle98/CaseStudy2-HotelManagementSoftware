package Views;

import Controller.Manager;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    public static void main(String[] args) {
       Menu();


    }

    static void Menu() {
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Chào mừng bạn đến với khách sạn thành công");
            System.out.println("1.Đăng nhập");
            System.out.println("2.Đăng ký");
            System.out.println("3.Thoát phần mềm");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    System.out.println(manager.login());
                    break;
                case "2":
                    manager.createAccount();
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
}
