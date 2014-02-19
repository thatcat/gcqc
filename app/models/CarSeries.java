package models;
import java.util.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

@Entity
@Table(name="CarSeries")
public class CarSeries extends Model {
    
    @Required
    @MaxSize(15)
    public String seriesName;
    
    @Required
    public String path;

	@Required
    public float bottomPrice;

	public float fuelConsumption;

	public float startLevel;

	@Required
	public String carType;


    public CarBrand(String brandName,int parise ) {
	this.brandName=brandName;
	this.parise=parise;
    }

    public String toString()  {
        return seriesName ;
    }
}
