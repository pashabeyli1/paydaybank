package az.fuadp.paydaybank.request;

import lombok.Data;

import java.util.Date;

@Data
public class ReqSignUp {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phone;
    private Date   dob;

}
