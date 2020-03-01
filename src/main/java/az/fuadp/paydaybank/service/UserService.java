package az.fuadp.paydaybank.service;

import az.fuadp.paydaybank.request.ReqAccount;
import az.fuadp.paydaybank.request.ReqSignIn;
import az.fuadp.paydaybank.request.ReqSignUp;
import az.fuadp.paydaybank.response.RespStatus;
import az.fuadp.paydaybank.response.RespUser;

import java.util.List;


public interface UserService {

    RespStatus signUp(ReqSignUp reqSignUp);

    RespUser signIn(ReqSignIn reqSignIn);

    List<RespUser> getUserList();
}
