package controllers;

import java.util.*;
import play.mvc.*;
import play.data.validation.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import play.*;
import models.*;

public class Logined extends Controller {
    @Before
    static void addUser() {
        User user = connected();
        if(user != null) {
            renderArgs.put("user", user);
        }
    }
    
    static User connected() {
        if(renderArgs.get("user") != null) {
            return renderArgs.get("user", User.class);
        }
        String username = session.get("user");
        if(username != null) {
            return User.find("byUsername", username).first();
        } 
        return null;
    }

	@Before
	static void checkLogined() { 
		    if(connected() == null) {
            //跳转到登录画面
            Application.login();
        }
	}
	
	/**添加管理员权限拦截，不是管理员，不能执行以下字符串数组内的方法*/
	@Before(only={"order_cms","post_cms","deletePost","deleteComment","deal_refresh","auth_delete","addSeries","saveCarSeries"}) 
	static void checkAdmin() { 
		User user = connected();
		if(user.isAdmin != true) {
			notFound();
        }
	}

    public static void index() {
		List<Post> postList = Post.find("order by id desc").from(0).fetch(3);	
		List<CarBrand> carBrandList = CarBrand.findAll();
       render(postList,carBrandList);
    }
    
    public static void logout() {
        session.clear();
        Application.index();
    }
    
    public static void email(String message) {
    	render(message);
    }

    public static void sendEmail(@Valid @Email String fromEmail,String title, String contentTexts){
        SendEmail sendEmailInfo = new SendEmail(fromEmail, title, contentTexts);
        sendEmailInfo.send();
        email("发送成功！");
    }
    
    public static void order_cms(){
		 //还没有处理的车主认证
    	List<User> userUndealList = User.find("byIsDealAndAuthPictiurePathIsNotNull",false).fetch();
    	//已经处理的车主认证
    	List<User> userDealedList = User.find("byIsDealAndAuthPictiurePathIsNotNull",true).fetch();
       render(userUndealList,userDealedList);
    }

public static void savePostOrigin(Post post) {

}


/**
  *在“更多留言”保存留言的方法
  */
    public static void savePost( Post post) {
		User author=null;
        String userName = session.get("user");  
		
		 if(userName != null) {
            author=User.find("byUsername", userName).first();
        } 
	
        post.author = author;  
		post.postedAt= new Date();
        // Save
        post.save();     
		flash.success("Thanks for posting %s", author);
		words_board();
    }

/**
  *在首页保存留言的方法
  */
	public static void savePost2( Post post) {
		User author=null;
        String userName = session.get("user");  
		
		 if(userName != null) {
            author=User.find("byUsername", userName).first();
        } 
	
        post.author = author;  
		post.postedAt= new Date();
        // Save
        post.save();     
		flash.success("Thanks for posting %s", author);
		index();
    }

/**
  *管理员页面保存留言的方法
  */
	 public static void savePostForCMS( Post post) {
		User author=null;
        String userName = session.get("user");  
		
		 if(userName != null) {
            author=User.find("byUsername", userName).first();
        } 
	
        post.author = author;  
		post.postedAt= new Date();
        // Save
        post.save();     
		flash.success("Thanks for posting %s", author);
		post_cms();
		
    }

	public static void words_board(){
    	//从form+1开始返回后面的fetch条记录
    	List<Post> postList = Post.find("order by id desc").fetch();  	
        render(postList);
    }

	/**各个页面添加评论的通用代码
	*/
	public static void originComment (Long postId, String content ) {
        Post post = Post.findById(postId);
		User author = null;
		String userName = session.get("user");
		 if(userName != null) {
			 author=User.find("byUsername", userName).first();
        } 
		Comment comment = new Comment(post,author,content);
		comment.save();
        flash.success("Thanks for posting %s", author);

	}

/**
  *在首页的留言评论
  */
	 public static void postComment(Long postId, String content ) {
        Post post = Post.findById(postId);
		User author = null;
		String userName = session.get("user");
		 if(userName != null) {
			 author=User.find("byUsername", userName).first();
        } 
		Comment comment = new Comment(post,author,content);
		comment.save();
        flash.success("Thanks for posting %s", author);
        words_board();
    }

/**
  *在留言板的留言评论
  */
	 public static void postComment2(Long postId, String content) {
        Post post = Post.findById(postId);
		User author = null;
		String userName = session.get("user");
		 if(userName != null) {
			 author=User.find("byUsername", userName).first();
        } 
		Comment comment = new Comment(post,author,content);
		comment.save();
        flash.success("Thanks for posting %s", author);
		index();
    }

/**
  *管理员的留言评论
  */
	 public static void postCommentForCMS(Long postId, String content) {
        Post post = Post.findById(postId);
		User author = null;
		String userName = session.get("user");
		 if(userName != null) {
			 author=User.find("byUsername", userName).first();
        } 
		Comment comment = new Comment(post,author,content);
		comment.save();
        flash.success("Thanks for posting %s", author);
        post_cms();
    }

	public static void post_cms( ) {
		List<Post> postList = Post.find("order by id desc").fetch();
       render(postList);
	}

	 public static void deletePost( Long postId) {
        Post post = Post.findById(postId);
		post.delete();
        post_cms();
    }

	 public static void deleteComment(Long commentId) {
		Comment comment=Comment.findById(commentId);
		comment.delete();
        post_cms();
    }

	 public static void order_customer(){
       render();
    }

	public static void changePassword(String newPassword, String verifyNewPassword) {
		validation.required(verifyNewPassword);
        validation.equals(verifyNewPassword, newPassword).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@Logined.order_customer", verifyNewPassword);
         }
		 User user = connected();
		 newPassword=Md5Util.getMD5Str(newPassword);				//MD5加密
		 user.password=newPassword;
		 user.save();
		  flash.success("修改成功" );
		  order_customer();
		}

		/**上传图片
		  */
		 public static void uploadPhoto(String title, File photo) {
			String outputPah = Play.applicationPath.toString()+"/public/attachment/";
			try
			{		
			File outputFile = new File(outputPah+photo.getName());
			FileOutputStream fos = new FileOutputStream(outputFile);
			FileInputStream fis = new FileInputStream(photo);

			byte[] buffer = new byte[10240];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
			 flash.success("上传成功" );
			}
			catch (FileNotFoundException  e )
			{
				e.printStackTrace(); 
			}
			catch (IOException  e )
			{
				e.printStackTrace(); 
			}
			catch(NullPointerException e)
			 {
				e.printStackTrace(); 
			 }

			User user = connected();
			user.authPictiurePath=outputPah;
			user.isDeal=false;
			user.applyDate=new Date();
			user.save();	
			order_customer();
	 }

	/**处理认证通不通过，isCarOwner为true表示通过，为false表示不通过
	  */
	public static void deal_refresh(long itemId, String authType,boolean isCarOwner){
    	//找到对应数据项
    	User user = User.findById(itemId);
		// 是否已经被处理设为true
		user.isDeal=true; 
		//这次认证通过
		if(isCarOwner == true) {
			user.authType=authType;
			user.isCarOwner=true;
			user.integration=user.integration+20;
		user.authDate=new Date();
		}
    	user.save();
    	Map map = new HashMap();
    	int status = 200;
    	map.put("status", status);
    	renderJSON(map);
    }

	/**取消认证
	  */
	public static void auth_delete(long itemId) {
    	//找到对应数据项
		int status=0;
    	User user = User.findById(itemId);
		if(user != null) {
		user.isDeal=true;
		user.isCarOwner=false;
		user.authType=null;
		user.save();
		status=200;
		}
		Map map = new HashMap();    	
   		map.put("status", status);
    	renderJSON(map); 	
	}

	public static void search(String carType,String lowPrice,String highPrice) {
		Application.search( carType, lowPrice, highPrice);
	}

	public static void addSeries(String message) {
		render(message);	
	}

	public static void saveCarSeries(CarSeries carSeries, Long carBrandId) {
		CarSeries carSeriesExisted = CarSeries.find("bySeriesName", carSeries.seriesName).first(); 
		String message = "";
		if(carSeriesExisted != null)  {
			message = "系列已经存在！添加失败";
			addSeries(message);
		}
		CarBrand carBrand = CarBrand.find("byId", carBrandId).first();
		carSeries.brandName=carBrand;
		carSeries.save();
		message = "添加成功！";
		addSeries(message);	
	}


}