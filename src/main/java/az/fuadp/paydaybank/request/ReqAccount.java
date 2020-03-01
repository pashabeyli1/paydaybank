package az.fuadp.paydaybank.request;

import lombok.Data;

import java.util.Date;

@Data
public class ReqAccount {

    private Long userId;
    private String token;
    private String accountNumber;
    private Double balance;
    private Long   accountTypeId;
    private String   createdDate;
    private Integer active; // 1 - active , 0 - inactive

}
