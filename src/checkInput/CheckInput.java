package checkInput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class CheckInput {
    private final Pattern CHECK_ACCOUNT = Pattern.compile("^\\w{6,12}$");
    private final Pattern CHECK_ID_CARD = Pattern.compile("^[0-9]{10}$");
    private final Pattern CHECK_PRICE = Pattern.compile("");

    public boolean checkAccountName(String input){
        Matcher matcher = CHECK_ACCOUNT.matcher(input);
        return matcher.find();
    }
    public boolean checkIDCard(String input){
        Matcher matcher = CHECK_ACCOUNT.matcher(input);
        return matcher.find();
    }


}
