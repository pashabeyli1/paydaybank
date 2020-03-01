package az.fuadp.paydaybank.dao;

import az.fuadp.paydaybank.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TransactionDao extends CrudRepository<Transaction,Long> {


    List<Transaction> findAllByActive(Integer active);

}
