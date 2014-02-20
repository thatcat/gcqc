package models;
import java.util.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

@Entity
@Table(name="User")
public class User extends Model {
    
    @Required
    @MaxSize(15)
    @MinSize(4)
    @Match(value="^\\w*$", message="Not a valid username")
    public String username;
    
    @Required
    @MaxSize(15)
    @MinSize(5)
    public String password;
    
    @Required
    @MaxSize(100)
	@Email
    public String email;
    
    public boolean isAdmin;

	public boolean isCarOwer;//是否为车主

	public String authType;

	public String authPictiurePath;//认证的图片路径

	public boolean isDeal;//是否已经被处理了认证

	public int integration; //积分

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    public List<CarComment> carComments;
   
    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.isAdmin = false;
    }

    public String toString()  {
        return username ;
    }

    
}
