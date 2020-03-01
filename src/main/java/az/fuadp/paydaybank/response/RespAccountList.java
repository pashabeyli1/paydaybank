package az.fuadp.paydaybank.response;

import lombok.Data;

import java.util.List;

@Data
public class RespAccountList {

    private List<RespAccount> accountList;
    private RespStatus status;


}
