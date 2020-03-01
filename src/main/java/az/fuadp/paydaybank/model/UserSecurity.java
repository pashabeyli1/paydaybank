package az.fuadp.paydaybank.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_security")
@DynamicInsert
public class UserSecurity {

    @Id
    @GeneratedValue
    private Long idUserSecurity;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer emailConfirm;
    private Integer mobileConfirm;
    private Date dataDate;
    @ColumnDefault(value = "1")
    private Integer active; // 1-active ,  0-deactive

}
