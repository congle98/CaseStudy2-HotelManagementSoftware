package HotelService;

public class MassageService implements Service{
    private static MassageService INSTANCE;
    private MassageService(){

    }
    public static MassageService getINSTANCE(){
        if(INSTANCE==null) INSTANCE = new MassageService();
        return INSTANCE;
    }

    @Override
    public String getDescribe() {
        return "Massage toàn thân";
    }

    @Override
    public double getPrice() {
        return 200;
    }
}
