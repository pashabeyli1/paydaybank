package az.fuadp.paydaybank.service;

import az.fuadp.paydaybank.request.ReqAccount;
import az.fuadp.paydaybank.request.ReqAccountType;
import az.fuadp.paydaybank.request.ReqTransaction;
import az.fuadp.paydaybank.request.ReqUser;
import az.fuadp.paydaybank.response.RespAccountList;
import az.fuadp.paydaybank.response.RespStatus;
import az.fuadp.paydaybank.response.RespTransactionList;

public interface AccountService {

    RespStatus openAccount(ReqAccount reqAccount);

    RespAccountList accountSummary(ReqUser reqUser);

    RespTransactionList transactionList(ReqTransaction reqTransaction);

    RespStatus addAccountType(ReqAccountType reqAccountType);
}
