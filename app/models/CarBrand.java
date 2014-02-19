package models;
import java.util.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

@Entity
@Table(name="CarBrand")
public class CarBrand extends Model {
    
    @Required
    @MaxSize(15)
    public String brandName;
    
    @Required
    public int parise;

    public CarBrand(String brandName,int parise ) {
	this.brandName=brandName;
	this.parise=parise;
    }

    public String toString()  {
        return brandName ;
    }
}
