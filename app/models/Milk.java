package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Milk extends Model {
	@Required
	public String milkName;
	@Required
	public int size;


	@Lob
	public String content;

	public Milk(String milkName,int size,String content){

		this.milkName = milkName;
		this.size = size;
		this.content = content;
	}

}