package az.fuadp.paydaybank.request;

import lombok.Data;

@Data
public class ReqTransaction {

    private Long accountId;
    private Long userId;
    private String token;


}
