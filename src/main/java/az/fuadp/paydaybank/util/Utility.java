package az.fuadp.paydaybank.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static boolean checkPassword(String password) {
        String regex = "^(.{5,})[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean sendMail(String from, String to,String text) {

        // Mail sender function
        return true;
    }


}
