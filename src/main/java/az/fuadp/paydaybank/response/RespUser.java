package az.fuadp.paydaybank.response;

import lombok.Data;

import java.util.Date;

@Data
public class RespUser {

    private Long userId;
    private String username;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String address;
    private String token;
    private RespStatus status;

}
