package az.fuadp.paydaybank.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "transaction")
@Entity
@DynamicInsert
public class Transaction {

    @Id
    @GeneratedValue
    private Long idTransaction;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private Date transactionDate;
    private Double amount;
    private String description;
    @ColumnDefault(value = "1")
    private Integer active;


}
