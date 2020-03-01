package az.fuadp.paydaybank.service;

import az.fuadp.paydaybank.dao.*;
import az.fuadp.paydaybank.enums.*;
import az.fuadp.paydaybank.exception.ExceptionConstants;
import az.fuadp.paydaybank.model.*;
import az.fuadp.paydaybank.request.ReqAccount;
import az.fuadp.paydaybank.request.ReqAccountType;
import az.fuadp.paydaybank.request.ReqTransaction;
import az.fuadp.paydaybank.request.ReqUser;
import az.fuadp.paydaybank.response.*;
import az.fuadp.paydaybank.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:config.properties")
public class AccountServiceImpl implements AccountService {


    @Autowired
    private UserLoginHistoryDao userLoginHistoryDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AccountTypeDao accountTypeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSecurityDao userSecurityDao;

    @Value( "${mail.from}" )
    private String mailFrom;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public RespStatus openAccount(ReqAccount reqAccount) {
        RespStatus response = null;
        try {
            if (reqAccount.getUserId() == null || reqAccount.getToken() == null ||
                    reqAccount.getAccountNumber() == null || reqAccount.getAccountTypeId() == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
                return response;
            }

            User user = userDao.findById(reqAccount.getUserId()).orElse(null);
            if (user == null) {
                response = new RespStatus(ExceptionConstants.USER_NOT_FOUND, "User not found");
                return response;
            }
            UserLoginHistory userLoginHistory = userLoginHistoryDao.findUserLoginHistoryByUserAndToken(user, reqAccount.getToken());
            if (userLoginHistory == null) {
                response = new RespStatus(ExceptionConstants.USER_TOKEN_IS_INVALID, "User token is invalid");
                return response;
            }
            Account account = new Account();
            account.setAccountNumber(reqAccount.getAccountNumber());
            account.setUser(user);
            account.setBalance(reqAccount.getBalance());
            AccountType accountType = accountTypeDao.findById(reqAccount.getAccountTypeId()).orElse(null);
            if (accountType == null) {
                response = new RespStatus(ExceptionConstants.ACCOUNT_TYPE_IS_INVALID, "Account type is invalid");
                return response;
            }
            account.setAccountType(accountType);
            account.setCreatedDate(df.parse(reqAccount.getCreatedDate()));
            account.setActive(EnumAccountStatus.ACTIVE.getValue());
            accountDao.save(account);
            UserSecurity userSecurity = userSecurityDao.findUserSecuritiesByUserAndActive(user, EnumUserStatus.ACTIVE.getValue());
            if (userSecurity.getEmailConfirm() == EnumEmailConfirm.ENABLE.getValue()) {
                String text = "Your account details: Name: "+user.getName()+" Surname: "+user.getSurname()+" phone: "+user.getPhone()+
                        " account number: "+reqAccount.getAccountNumber()+" account type: "+accountType.getName();
                System.out.println("Text: "+text);
                Utility.sendMail(mailFrom, user.getEmail(), text);
            }
            response = RespStatus.getSuccessMessage();
        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespAccountList accountSummary(ReqUser reqUser) {
        RespAccountList response = new RespAccountList();
        List<RespAccount> respAccountList = new ArrayList<>();
        String accountStatus;
        try {
            if (reqUser.getUserId() == null || reqUser.getToken() == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return response;
            }
            User user = userDao.findById(reqUser.getUserId()).orElse(null);
            if (user == null) {
                response.setStatus(new RespStatus(ExceptionConstants.USER_NOT_FOUND, "User not found"));
                return response;
            }
            UserLoginHistory userLoginHistory = userLoginHistoryDao.findUserLoginHistoryByUserAndToken(user, reqUser.getToken());
            if (userLoginHistory == null) {
                response.setStatus(new RespStatus(ExceptionConstants.USER_TOKEN_IS_INVALID, "User token is invalid"));
                return response;
            }
            List<Account> accountList = (List<Account>) accountDao.findAll();
            if (accountList.size() == 0) {
                response.setStatus(new RespStatus(ExceptionConstants.ACCOUNT_NOT_FOUND, "Account not found"));
                return response;
            }
            for (Account account : accountList) {
                RespAccount respAccount = new RespAccount();
                respAccount.setAccountId(account.getIdAccount());
                respAccount.setAccountNumber(account.getAccountNumber());
                respAccount.setAccountType(account.getAccountType().getName());
                respAccount.setBalance(account.getBalance());
                if (account.getActive() == EnumAccountStatus.ACTIVE.getValue())
                    accountStatus = EnumAccountStatus.ACTIVE.name();
                else
                    accountStatus = EnumAccountStatus.INACTIVE.name();
                respAccount.setAccountStatus(accountStatus);
                respAccountList.add(respAccount);
            }
            response.setAccountList(respAccountList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespTransactionList transactionList(ReqTransaction reqTransaction) {
        RespTransactionList response = new RespTransactionList();
        List<RespTransaction> respTransactionList = new ArrayList<>();
        try {
            if (reqTransaction.getUserId() == null || reqTransaction.getToken() == null
                    || reqTransaction.getAccountId() == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return response;
            }
            User user = userDao.findById(reqTransaction.getUserId()).orElse(null);
            if (user == null) {
                response.setStatus(new RespStatus(ExceptionConstants.USER_NOT_FOUND, "User not found"));
                return response;
            }
            UserLoginHistory userLoginHistory = userLoginHistoryDao.findUserLoginHistoryByUserAndToken(user, reqTransaction.getToken());
            if (userLoginHistory == null) {
                response.setStatus(new RespStatus(ExceptionConstants.USER_TOKEN_IS_INVALID, "User token is invalid"));
                return response;
            }
            List<Transaction> transactionList = transactionDao.findAllByActive(EnumTransactionStatus.ACTIVE.getValue());
            if (transactionList.size() == 0) {
                response.setStatus(new RespStatus(ExceptionConstants.TRANSACTION_NOT_FOUND, "Transaction not found"));
                return response;
            }
            for (Transaction transaction : transactionList) {
                RespTransaction respTransaction = new RespTransaction();
                respTransaction.setTransactionId(transaction.getIdTransaction());
                respTransaction.setTransactionDate(transaction.getTransactionDate());
                respTransaction.setAmount(transaction.getAmount());
                respTransaction.setDescription(transaction.getDescription());
                respTransactionList.add(respTransaction);
            }
            response.setRespTransactionList(respTransactionList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespStatus addAccountType(ReqAccountType reqAccountType) {
        RespStatus response = null;
        try {
            if (reqAccountType.getUserId() == null || reqAccountType.getToken() == null
                    || reqAccountType.getName() == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
                return response;
            }
            User user = userDao.findById(reqAccountType.getUserId()).orElse(null);
            if (user == null) {
                response = new RespStatus(ExceptionConstants.USER_NOT_FOUND, "User not found");
                return response;
            }
            UserLoginHistory userLoginHistory = userLoginHistoryDao.findUserLoginHistoryByUserAndToken(user, reqAccountType.getToken());
            if (userLoginHistory == null) {
                response = new RespStatus(ExceptionConstants.USER_TOKEN_IS_INVALID, "User token is invalid");
                return response;
            }
            AccountType accountType = new AccountType();
            accountType.setName(reqAccountType.getName());
            accountTypeDao.save(accountType);
            response = RespStatus.getSuccessMessage();
        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");
            ex.printStackTrace();
        }
        return response;
    }


}
