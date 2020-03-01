package az.fuadp.paydaybank.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
@DynamicInsert
public class User {

    @Id
    @GeneratedValue
    private Long idUser;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String address;
    private Date   dob;
    private String phone;
    private Date   dataDate;
    @ColumnDefault(value = "1")
    private Integer active; // 1-active ,  0-deactive

}
