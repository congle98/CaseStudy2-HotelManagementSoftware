package Model;

public class Account {
    private String accountName;
    private String accountPassword;
    private String accountEmail;

    public Account() {
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    @Override
    public String toString() {
        return "Thông tin tài khoản \n"+
                "Tên đăng nhập: "+accountName+"\n"+
                "Mật khẩu: "+accountPassword+"\n"+
                "Email: "+accountEmail;
    }
}
