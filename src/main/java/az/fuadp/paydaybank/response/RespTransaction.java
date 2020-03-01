package az.fuadp.paydaybank.response;

import lombok.Data;

import java.util.Date;

@Data
public class RespTransaction {

    private Long  transactionId;
    private Date transactionDate;
    private Double amount;
    private String description;

}
