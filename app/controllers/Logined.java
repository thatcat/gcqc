package controllers;

import java.util.*;
import play.mvc.*;
import play.data.validation.*;
import play.db.jpa.*;
import javax.persistence.*;

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
    	
       render(postList);
    }
    
    public static void logout() {
        session.clear();
        Application.index();
    }
    
    public static void email(String message) {
    	User user = connected();
    	if(user == null) {
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
    
    public static void order(String message){
    	render(message);
    }
    
    public static void order_cms(){
    	if(connected() == null) {
            //跳转到登录画面
            Application.index();
        }
		
    	//找到已经送货的订单
    	List<Ordered> orderedSendedList = Ordered.find("bySended",true).fetch();
    	//找到还没送货的订单
    	List<Ordered> orderedNoSendedList = Ordered.find("bySended",false).fetch();
       render(orderedSendedList,orderedNoSendedList);
    }

    public static void order_refresh(long itemId){
    	if(connected() == null) {
            //跳转到登录画面
            Application.index();
        }
		
    	//找到对应数据项
    	Ordered ordered = Ordered.findById(itemId);
    	ordered.sended = true;
    	ordered.messages = "555";
    	ordered.cupCount = 123;
    	ordered.save();
    	//order_cms();
    	Map map = new HashMap();
    	int status = 200;
    	map.put("status", status);
    	renderJSON(map);
		
    }    
    
    public static void order_dele(long itemId){
    	if(connected() == null) {
            //跳转到登录画面
            Application.index();
        }
		
    	//找到对应数据项
    	int status = 0;
    	Ordered ordered = Ordered.findById(itemId);
    	if(ordered != null){
    		ordered.delete();
        	status = 200;
    	}
    	Map map = new HashMap();    	
   		map.put("status", status);
    	renderJSON(map); 	
		
    }
    
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
                
        // Validate
        //validation.valid(post);
        //if(validation.hasErrors()) {
        //   render("@form", post);
        //}

        // Save
        post.save();     
		flash.success("Thanks for posting %s", author);
		words_board();
		
    }

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
                
        // Validate
        //validation.valid(post);
        //if(validation.hasErrors()) {
        //   render("@form", post);
        //}

        // Save
        post.save();     
		flash.success("Thanks for posting %s", author);
		index();
    }

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
                
        // Validate
        //validation.valid(post);
        //if(validation.hasErrors()) {
        //   render("@form", post);
        //}

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

	public static void words_board2(){
    
    	//从form+1开始返回后面的fetch条记录
    	List<Post> postList = Post.find("order by id desc").from(0).fetch(10);
    	
       render(postList);
    }

	 public static void postComment(
        Long postId, String content
		 )
         
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

	 public static void postComment2(
        Long postId, String content
		 )
         
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

	 public static void postCommentForCMS(
        Long postId, String content
		 )
         
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
		//render();
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

	public static void myOrder()
	{	
		User user = connected();
		if(user == null)
		{
            //跳转到登录画面
            Application.login();
        }

		List<Ordered> orderList = Ordered.find("byUser", user).fetch();		
        render(orderList);
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

}