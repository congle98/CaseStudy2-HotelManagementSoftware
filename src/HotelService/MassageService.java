package HotelService;

public class MassageService implements Service{

    @Override
    public String getDescribe() {
        return "Massage toàn thân";
    }

    @Override
    public double getPrice() {
        return 200;
    }
}
