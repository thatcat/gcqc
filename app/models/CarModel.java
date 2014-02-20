package models;
import java.util.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

/**Æû³µÐÍºÅÀà
  */
@Entity
@Table(name="CarModel")
public class CarModel extends Model {
    
    @Required
    @MaxSize(15)
    public String brandName;
    
    @Required
    public int parise;

	@Required
    @ManyToOne
    public CarSeries seriesName;

    public String toString()  {
        return brandName ;
    }
}
