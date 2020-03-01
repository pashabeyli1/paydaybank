package az.fuadp.paydaybank.webservice;

import az.fuadp.paydaybank.request.ReqAccount;
import az.fuadp.paydaybank.request.ReqAccountType;
import az.fuadp.paydaybank.request.ReqTransaction;
import az.fuadp.paydaybank.request.ReqUser;
import az.fuadp.paydaybank.response.RespAccountList;
import az.fuadp.paydaybank.response.RespStatus;
import az.fuadp.paydaybank.response.RespTransactionList;
import az.fuadp.paydaybank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountWebService {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/addAccountType",
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public RespStatus addAccountType(@RequestBody ReqAccountType reqAccountType) {
        return accountService.addAccountType(reqAccountType);
    }

    @PostMapping(value = "/openAccount",
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public RespStatus openAccount(@RequestBody ReqAccount reqAccount) {
        return accountService.openAccount(reqAccount);
    }

    @PostMapping(value = "/accountSummary",
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public RespAccountList accountSummary(@RequestBody ReqUser reqUser) {
        return accountService.accountSummary(reqUser);
    }

    @PostMapping(value = "/transactionList",
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public RespTransactionList transactionList(@RequestBody ReqTransaction reqTransaction) {
        return accountService.transactionList(reqTransaction);
    }

}
