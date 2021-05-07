package hotelService;

import java.io.Serializable;

public class MudBathService implements Service{
    private static MudBathService INSTANCE;
    private MudBathService(){

    }
    public static MudBathService getINSTANCE(){
        if(INSTANCE==null) INSTANCE = new MudBathService();
        return INSTANCE;
    }

    @Override
    public String getDescribe() {
        return "Tắm bùn cao cấp";
    }

    @Override
    public double getPrice() {
        return 100;
    }
}
