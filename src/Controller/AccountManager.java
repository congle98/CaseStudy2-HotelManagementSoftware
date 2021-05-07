package Controller;

import Model.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
    private static Scanner scanner = new Scanner(System.in);
    private Account account;
    private  ArrayList<Account> listAccount = new ArrayList<>();
    private static AccountManager INSTANCE;

    private AccountManager(){

    }

    public String getAccountInformation() {
        return account.toString();
    }
    public static AccountManager getINSTANCE(){
        if(INSTANCE==null) INSTANCE = new AccountManager();
        return INSTANCE;
    }

    private String createAccountName(){
        System.out.println("Mời nhập tên tài khoản");
        String accountName = scanner.nextLine();
        for (Account a:listAccount
             ) {
            if((a.getAccountName().equals(accountName))){
                System.err.println("xin lỗi tên tài khoản đã tồn tại");
                return null;
            }
        }
        return accountName;

    }

    private String enterAccountPassword(){
        System.out.println("Mời nhập mật khẩu ");
        String accountPassword = scanner.nextLine();
        return accountPassword;
    }

    private String createAccountEmail(){
        System.out.println("Mời nhập email ");
        String accountEmail = scanner.nextLine();
        for (Account a:listAccount
        ) {
            if((a.getAccountEmail().equals(accountEmail))){
                System.err.println("xin lỗi một email chỉ tạo được 1 tài khoản");
                return null;
            }
        }
        return accountEmail;
    }

    public void createNewAccount(){
        Account account = new Account();
        String accountName = createAccountName();
        if(accountName!=null){
            String accountEmail = createAccountEmail();
            if(accountEmail!=null){
                account.setAccountPassword(enterAccountPassword());
                account.setAccountName(accountName);
                account.setAccountEmail(accountEmail);
                listAccount.add(account);
                System.err.println("chúc mừng bạn đã đăng ký thành công!!");
            }
        }
    }

    private String loginAccountName(){
        System.out.println("Mời nhập tên tài khoản");
        String accountName = scanner.nextLine();
        return accountName;
    }
    private String loginAccountEmail(){
        System.out.println("Mời nhập email ");
        String accountEmail = scanner.nextLine();
        return accountEmail;
    }
    public boolean login(){
        String accountName = loginAccountName();
        String accountPassword = enterAccountPassword();
        for (Account account: listAccount
        ) {
            if(account.getAccountName().equals(accountName)&&account.getAccountPassword().equals(accountPassword)){
                this.account = account;
                return true;
            }
        }
        return false;
    }

    public void passwordRetrieval(){
        System.err.println("Mời nhập lần lượt tên tài khoản và email");
        String accountName = loginAccountName();
        String accountEmail = loginAccountEmail();
        boolean check = false;
        for (Account a:listAccount
             ) {
            if(a.getAccountEmail().equals(accountEmail)&&a.getAccountName().equals(accountName)){
                check = true;
                System.err.println("Mật khẩu của tài khoản: "+a.getAccountName()+" là: "+a.getAccountPassword());
            }
        }
        if(!check){
            System.err.println("Xin lỗi tên đăng nhập và email không trùng khớp với bất kỳ tài khoản nào!");
        }
    }
    public void accountMenu(){
        Scanner scanner = new Scanner(System.in);
        String choose;
        do {
            System.out.println("1.Thay đổi mật khẩu");
            System.out.println("2.Thay đổi email");
            System.out.println("3.Thoát ra ngoài");
            choose = scanner.nextLine();
            switch (choose){
                case "1":
                    account.setAccountPassword(enterAccountPassword());
                    System.err.println("thiết lập thành công");
                    break;
                case "2":
                    account.setAccountEmail(createAccountEmail());
                    System.err.println("thiết lập thành công");
                    break;
                case "3":
                    break;
                default:
                    System.err.println("---------------Bạn nhập sai tuỳ chọn, mời nhập lại!!!---------------");

            }
        }while (!choose.equals("3"));
    }
}
