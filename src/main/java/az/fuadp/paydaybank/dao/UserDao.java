package az.fuadp.paydaybank.dao;

import az.fuadp.paydaybank.model.User;
import org.springframework.data.repository.CrudRepository;



public interface UserDao extends CrudRepository<User,Long> {


    User findUserByUsernameAndActive(String username,Integer active);

    User findUserByUsernameAndPasswordAndActive(String username,String password,Integer active);


}