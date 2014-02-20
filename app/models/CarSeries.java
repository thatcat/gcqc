package models;
import java.util.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

/**汽车系列类，一个品牌有多条点评和多个型号
  */
@Entity
@Table(name="CarSeries")
public class CarSeries extends Model {
    
    @Required
    @MaxSize(15)
    public String seriesName;
    
    @Required
    public String path;//图片路径

    public String bottomPrice;//最低价

	public String fuelConsumption;//油耗

	public float startLevel;//星级

	@Required
	public String carType;//车型

	@Required
    @ManyToOne
    public CarBrand brandName;

	@OneToMany(mappedBy="seriesName", cascade=CascadeType.ALL)
    public List<CarModel> carModels;

	@OneToMany(mappedBy="seriesName", cascade=CascadeType.ALL)
    public List<CarComment> carComments;

    public CarSeries(String seriesName,String path, String bottomPrice,String fuelConsumption,float startLevel, String carType) {
	this.seriesName=seriesName;
	this.path=path;
	this.bottomPrice=bottomPrice;
	this.fuelConsumption=fuelConsumption;
	this.startLevel=startLevel;
	this.carType=carType;
    }

    public String toString()  {
        return seriesName ;
    }
}
