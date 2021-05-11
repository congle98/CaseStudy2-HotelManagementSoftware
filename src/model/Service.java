package model;

import java.io.Serializable;
//Dependendy Inversion principle
public interface Service  extends Serializable {
    String getDescribe();
    double getPrice();

}
