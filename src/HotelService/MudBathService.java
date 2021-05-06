package HotelService;

public class MudBathService implements Service{
    @Override
    public String getDescribe() {
        return "Tắm bùn cao cấp";
    }

    @Override
    public double getPrice() {
        return 100;
    }
}
