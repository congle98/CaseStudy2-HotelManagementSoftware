package hotelService;

import java.io.Serializable;

public interface Service  extends Serializable {
    String getDescribe();
    double getPrice();

}
