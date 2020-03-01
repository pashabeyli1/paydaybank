package az.fuadp.paydaybank.response;

import lombok.Data;

import java.util.List;

@Data
public class RespTransactionList {

    private List<RespTransaction> respTransactionList;
    private RespStatus status;

}
