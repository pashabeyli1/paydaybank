package az.fuadp.paydaybank.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "account")
public class Account {

      @Id
      @GeneratedValue
      private Long idAccount;
      private String accountNumber;
      private Double balance;
      private Date createdDate;
      @ManyToOne
      @JoinColumn(name = "user_id")
      private User user;
      @ManyToOne
      @JoinColumn(name = "account_type_id")
      private AccountType accountType;
      private Integer active;

}



