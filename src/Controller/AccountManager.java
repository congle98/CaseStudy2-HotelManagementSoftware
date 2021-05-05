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

    private String enterAccountName(){
        System.out.println("Mời nhập tên tài khoản");
        String accountName = scanner.nextLine();
        return accountName;
    }

    private String enterAccountPassword(){
        System.out.println("Mời nhập mật khẩu ");
        String accountPassword = scanner.nextLine();
        return accountPassword;
    }

    private String enterAccountEmail(){
        System.out.println("Mời nhập email ");
        String accountEmail = scanner.nextLine();
        return accountEmail;
    }

    public void createNewAccount(){
        Account account = new Account();
        account.setAccountName(enterAccountName());
        account.setAccountPassword(enterAccountPassword());
        account.setAccountEmail(enterAccountEmail());
        listAccount.add(account);
        System.out.println("chúc mừng bạn đã đăng ký thành công!!");
    }

    public boolean login(){
        String accountName = enterAccountName();
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
}
