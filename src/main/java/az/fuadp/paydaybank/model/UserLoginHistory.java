package az.fuadp.paydaybank.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_login_history")
@DynamicInsert
public class UserLoginHistory {

    @Id
    @GeneratedValue
    private Long idUserLoginHistory;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String token;
    private Date loginDate;
    @ColumnDefault(value = "1")
    private Integer active; // 1-active ,  0-deactive
}
