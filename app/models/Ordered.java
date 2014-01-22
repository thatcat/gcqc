package models;

import java.util.Date;

import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

@Entity
@Table(name="Ordered")
public class Ordered extends Model {
    
    @Required
    @MaxSize(15)
    @MinSize(4)
    @Match(value="^\\w*$", message="Not a valid username")
    public String username;
    
    @Required
    @MaxSize(100)
    @MinSize(5)
    public String address;

    @Required
    public String milk;
    
    @Required
    @MinSize(4)
    @MaxSize(20)
    public long phone;
    
    public String messages;

    @MinSize(0)
    @MaxSize(1000)
    public int boxCount;
    
    @MinSize(0)
    @MaxSize(1000)
    public int cupCount;
    public Date date;
    public boolean sended;

	@ManyToOne
    @Required
    public User user;
    
    public Ordered(){
    	this.date = new Date();
    }
    public Ordered(String username, String address,long phone, String milk, int boxCount, int cupCount, String messages) {

    	this.username = username;
    	this.address = address;
    	this.phone = phone;
    	this.milk = milk;
        this.boxCount = boxCount;
        this.cupCount = cupCount;
        this.messages = messages;
    	this.date = new Date();
    }

    public boolean isSended(){
        return this.sended;
    }

    public void setSended(){
        this.sended = true;
    }

    public String toString()  {
        return "User(" + username + ")";
    }
    
}
