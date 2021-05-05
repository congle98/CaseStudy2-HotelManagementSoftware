package Model;

public class Renter {
    private String name;
    private String idCard;
    private String phoneNumber;

    public Renter() {
    }

    public Renter(String name, String idCard, String phoneNumber) {
        this.name = name;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
