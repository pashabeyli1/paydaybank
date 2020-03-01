package az.fuadp.paydaybank.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account_type")
@DynamicInsert
public class AccountType {

    @Id
    @GeneratedValue
    private Long idAccountType;
    private String name;
    @ColumnDefault(value = "1")
    private Integer active;


}
