package entity.user;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAlias("id")
    private int id;
    @XStreamAlias("login")
    @Size(min=3 , max=30)
    private String login;
    @Size(min=6 , max=10)
    @XStreamAlias("password")
    private String password;
    @XStreamAlias("firstName")
    private String firstName;
    @XStreamAlias("lastName")
    private String lastName;
    @XStreamAlias("userName")
    private String userName;


    public User(){}

    public User(String login, String password, String firstName, String lastName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = (new StringBuilder(firstName).append(" ").append(lastName)).toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
