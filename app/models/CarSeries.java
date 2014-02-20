package models;
import java.util.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

/**����ϵ���࣬һ��Ʒ���ж��������Ͷ���ͺ�
  */
@Entity
@Table(name="CarSeries")
public class CarSeries extends Model {
    
    @Required
    @MaxSize(15)
    public String seriesName;
    
    @Required
    public String path;//ͼƬ·��

    public String bottomPrice;//��ͼ�

	public String fuelConsumption;//�ͺ�

	public float startLevel;//�Ǽ�

	@Required
	public String carType;//����

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
