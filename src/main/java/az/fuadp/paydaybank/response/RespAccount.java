package az.fuadp.paydaybank.response;

import lombok.Data;

@Data
public class RespAccount {

    private Long   accountId;
    private String accountType;
    private String accountNumber;
    private Double balance;
    private String accountStatus;

}
