package az.fuadp.paydaybank.dao;

import az.fuadp.paydaybank.model.User;
import az.fuadp.paydaybank.model.UserLoginHistory;
import org.springframework.data.repository.CrudRepository;

public interface UserLoginHistoryDao extends CrudRepository<UserLoginHistory,Long> {

    UserLoginHistory findUserLoginHistoryByUserAndToken(User user,String token);

}
