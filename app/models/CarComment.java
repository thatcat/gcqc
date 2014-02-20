package models;
import java.util.*;
import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;
import play.data.binding.*;

/**∆˚≥µµ„∆¿¿‡
  */
@Entity
@Table(name="CarComment")
public class CarComment extends Model {
    
    @Required
    public String commentType;
    
	@Required @As("yyyy-MM-dd")
	public Date carCommentATime;

	@Required
    @ManyToOne
    public CarSeries seriesName;

	@Required
    @ManyToOne
    public User user;


    public CarComment(String commentType,Date carCommentATime ) {
	this.commentType=commentType;
	this.carCommentATime=carCommentATime;
    }

    public String toString()  {
        return commentType ;
    }
}
