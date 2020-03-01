package az.fuadp.paydaybank.dao;

import az.fuadp.paydaybank.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountDao  extends CrudRepository<Account,Long> {
}
