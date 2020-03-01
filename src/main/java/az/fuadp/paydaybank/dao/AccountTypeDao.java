package az.fuadp.paydaybank.dao;

import az.fuadp.paydaybank.model.AccountType;
import org.springframework.data.repository.CrudRepository;

public interface AccountTypeDao extends CrudRepository<AccountType,Long> {
}
