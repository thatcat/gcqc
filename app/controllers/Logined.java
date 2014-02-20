package controllers;

import java.util.*;
import play.mvc.*;
import play.data.validation.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.io.*;

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

    public static void index() {
        if(connected() == null) {
            //跳转到登录画面
            Application.index();
        }
		List<Post> postList = Post.find("order by id desc").from(0).fetch(5);	
		List<CarBrand> carBrandList = CarBrand.findAll();
       render(postList,carBrandList);
    }
    
    public static void logout() {
        session.clear();
        Application.index();
    }
    
    public static void email(String message) {
    	User user = connected();
    	if(user == null) {
			 flash.error("请先登录！");
            //跳转到登录画面
            Application.login();
        }
    	render(message);
    }

    public static void sendEmail(@Valid @Email String fromEmail,String title, String contentTexts){
        SendEmail sendEmailInfo = new SendEmail(fromEmail, title, contentTexts);
        sendEmailInfo.send();
        email("发送成功！");
    }
    
    public static void order_cms(){
    	if(connected() == null) {
            //跳转到登录画面
            Application.index();
        }
       render();
    }

public static void savePostOrigin(Post post) {

}


/**
  *在首页保存留言的方法
  */
    public static void savePost( Post post) {
		if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }		
        
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
  *在“更多留言”保存留言的方法
  */
	public static void savePost2( Post post) {
		if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }		
        
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
		if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }		
        
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

/**
  *在首页的留言评论
  */
	 public static void postComment(Long postId, String content )
    {
		if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }	
		//String content = content;
        Post post = Post.findById(postId);
		String author = session.get("user");
        post.addComment(author, content);
        flash.success("Thanks for posting %s", author);
        words_board();
    }

/**
  *在留言板的留言评论
  */
	 public static void postComment2(Long postId, String content)
    {
		if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }	
		//String content = content;
        Post post = Post.findById(postId);
		String author = session.get("user");
        post.addComment(author, content);
        flash.success("Thanks for posting %s", author);
        index();
    }

/**
  *管理员的留言评论
  */
	 public static void postCommentForCMS(Long postId, String content)
     {
		if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }	
		//String content = content;
        Post post = Post.findById(postId);
		String author = session.get("user");
        post.addComment(author, content);
        flash.success("Thanks for posting %s", author);
        post_cms();
    }

	public static void post_cms( ) {
		List<Post> postList = Post.find("order by id desc").from(0).fetch(10);
       render(postList);
	}

	 public static void deletePost( Long postId)       
    {
		if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }	
		//String content = content;
        Post post = Post.findById(postId);
		post.delete();
        post_cms();
    }

	 public static void deleteComment(Long commentId)       
    {
		if(connected()==null)
		{
            //跳转到登录画面
            Application.login();
        }	
		//String content = content;
       // Post post = Post.findById(postId);
		Comment comment=Comment.findById(commentId);
		comment.delete();
        post_cms();
    }

	
	 public static void order_customer(){
		User user = connected();
    	if(user == null) {
            //跳转到登录画面
            Application.index();
        }		
       render();
    }
		public static void changePassword(String newPassword, String verifyNewPassword) {
		validation.required(verifyNewPassword);
        validation.equals(verifyNewPassword, newPassword).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@Logined.order_customer", verifyNewPassword);
         }
		  flash.success("修改成功" );
		}

		 public static void uploadPhoto(String title, File photo) {

			System.out.println("-----title("+title+")title------");

			System.out.println("photo"+photo);
			
///**
				try {  
				FileOutputStream outStream = new FileOutputStream("D:/gcqc/tmp/uploads/2.jpg");  
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);  
				  
				objectOutputStream.writeObject(photo);  
				outStream.close();  
				System.out.println("successful");  
			} catch (FileNotFoundException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  

//*/


			order_customer();
	 }




}