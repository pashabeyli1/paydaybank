package az.fuadp.paydaybank.dao;

import az.fuadp.paydaybank.model.User;
import az.fuadp.paydaybank.model.UserSecurity;
import org.springframework.data.repository.CrudRepository;

public interface UserSecurityDao extends CrudRepository<UserSecurity,Long> {


    UserSecurity findUserSecuritiesByUserAndActive(User user,Integer active);

}
