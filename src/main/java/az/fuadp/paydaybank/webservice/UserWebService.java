package az.fuadp.paydaybank.webservice;

import az.fuadp.paydaybank.request.ReqSignIn;
import az.fuadp.paydaybank.request.ReqSignUp;
import az.fuadp.paydaybank.response.RespStatus;
import az.fuadp.paydaybank.response.RespUser;
import az.fuadp.paydaybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserWebService {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signUp",
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public RespStatus signUp(@RequestBody ReqSignUp reqSignUp) {
        return userService.signUp(reqSignUp);
    }


    @PostMapping(value = "/signIn",
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public RespUser signIn(@RequestBody ReqSignIn reqSignIn) {
        return userService.signIn(reqSignIn);
    }


    @GetMapping(value = "/getUserList",
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public List<RespUser> getUserList() {
        return userService.getUserList();
    }




}
