package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class CheckInput {
    private static  CheckInput INSTANCE;

    private  CheckInput(){

    }
    public static CheckInput getINSTANCE(){
        if(INSTANCE==null) INSTANCE = new CheckInput();
        return INSTANCE;
    }
    private final Pattern CHECK_ACCOUNT = Pattern.compile("^\\w{6,12}$");
    private final Pattern CHECK_ID_CARD = Pattern.compile("^[0-9]{10}$");
    private final Pattern CHECK_EMAIL = Pattern.compile("^[a-zA-z0-9]+@[a-zA-z0-9]+(\\.[a-zA-z0-9]+){1,2}$");
    private final Pattern CHECK_DATE = Pattern.compile("^[0-3][0-9]/[0-1][0-9]/[0-9]{4}$");
    private final Pattern CHECK_INT = Pattern.compile("^[0-9]+$");

    public boolean checkAccountNamePassword(String input){
        Matcher matcher = CHECK_ACCOUNT.matcher(input);
        return matcher.find();
    }
    public boolean checkIDCard(String input){
        Matcher matcher = CHECK_ID_CARD.matcher(input);
        return matcher.find();
    }
    public boolean checkEmail(String input){
        Matcher matcher = CHECK_EMAIL.matcher(input);
        return matcher.find();
    }
    public boolean checkDate(String input){
        Matcher matcher = CHECK_DATE.matcher(input);
        return matcher.find();
    }
    public boolean checkINT(String input){
        Matcher matcher = CHECK_INT.matcher(input);
        return matcher.find();
    }


}
