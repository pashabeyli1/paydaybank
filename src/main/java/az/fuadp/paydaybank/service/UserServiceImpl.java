package az.fuadp.paydaybank.service;

import az.fuadp.paydaybank.dao.UserDao;
import az.fuadp.paydaybank.dao.UserLoginHistoryDao;
import az.fuadp.paydaybank.dao.UserSecurityDao;
import az.fuadp.paydaybank.enums.EnumAccountType;
import az.fuadp.paydaybank.enums.EnumEmailConfirm;
import az.fuadp.paydaybank.enums.EnumUserStatus;
import az.fuadp.paydaybank.exception.ExceptionConstants;
import az.fuadp.paydaybank.model.User;
import az.fuadp.paydaybank.model.UserLoginHistory;
import az.fuadp.paydaybank.model.UserSecurity;
import az.fuadp.paydaybank.request.ReqSignIn;
import az.fuadp.paydaybank.request.ReqSignUp;
import az.fuadp.paydaybank.response.RespStatus;
import az.fuadp.paydaybank.response.RespUser;
import az.fuadp.paydaybank.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSecurityDao userSecurityDao;

    @Autowired
    private UserLoginHistoryDao userLoginHistoryDao;


    @Override
    public RespStatus signUp(ReqSignUp reqSignUp) {
        RespStatus response = null;
        User user = null;
        try {
            if (reqSignUp.getUsername() == null || reqSignUp.getPassword() == null ||
                    reqSignUp.getName() == null || reqSignUp.getSurname() == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
                return response;
            }
            user = userDao.findUserByUsernameAndActive(reqSignUp.getUsername(), EnumUserStatus.ACTIVE.getValue());
            if (user != null) {
                response = new RespStatus(ExceptionConstants.USER_IS_ALREADY_EXIST, "User is already exist");
                return response;
            }
            if (!Utility.checkPassword(reqSignUp.getPassword())) {
                response = new RespStatus(ExceptionConstants.PASSWORD_FORMAT_IS_INCORRECT, "Password format is incorrect");
                return response;
            }
            user = new User();
            user.setUsername(reqSignUp.getUsername());
            user.setPassword(reqSignUp.getPassword());
            user.setName(reqSignUp.getName());
            user.setSurname(reqSignUp.getSurname());
            user.setAddress(reqSignUp.getAddress());
            user.setPhone(reqSignUp.getPhone());
            user.setDob(reqSignUp.getDob());
            user.setEmail(reqSignUp.getEmail());
            userDao.save(user);

            response = RespStatus.getSuccessMessage();
        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespUser signIn(ReqSignIn reqSignIn) {
        RespUser response = new RespUser();
        User user = null;
        try {
            if (reqSignIn.getUsername() == null || reqSignIn.getPassword() == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return response;
            }
            user = userDao.findUserByUsernameAndActive(reqSignIn.getUsername(), EnumUserStatus.ACTIVE.getValue());
            if (user == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_USERNAME, "Invalid username"));
                return response;
            }

            user = userDao.findUserByUsernameAndPasswordAndActive(reqSignIn.getUsername(),reqSignIn.getPassword(), EnumUserStatus.ACTIVE.getValue());
            if (user == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_PASSWORD, "Invalid password"));
                return response;
            }
            String token = UUID.randomUUID().toString();
            UserSecurity userSecurity = new UserSecurity();
            userSecurity.setUser(user);
            userSecurity.setEmailConfirm(EnumEmailConfirm.ENABLE.getValue());
            userSecurityDao.save(userSecurity);
            UserLoginHistory userLoginHistory = new UserLoginHistory();
            userLoginHistory.setUser(user);
            userLoginHistory.setToken(token);
            userLoginHistoryDao.save(userLoginHistory);
            response.setUserId(user.getIdUser());
            response.setUsername(user.getUsername());
            response.setName(user.getName());
            response.setSurname(user.getSurname());
            response.setEmail(user.getEmail());
            response.setToken(token);
            response.setStatus(RespStatus.getSuccessMessage());


        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public List<RespUser> getUserList() {
        List<RespUser> response = new ArrayList<>();
        try {
            List<User> userList = (List<User>) userDao.findAll();
            for (User user : userList) {
                RespUser respUser = new RespUser();
                respUser.setUserId(user.getIdUser());
                respUser.setName(user.getName());
                respUser.setSurname(user.getSurname());
                respUser.setUsername(user.getUsername());
                respUser.setEmail(user.getEmail());
                respUser.setPhone(user.getPhone());
                response.add(respUser);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
